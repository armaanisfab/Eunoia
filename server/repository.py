from dotenv import load_dotenv
import os
from fastapi import HTTPException
from fastapi.concurrency import run_in_threadpool
from models import Feedback, Entry
from supabase import create_client, Client
import ollama

load_dotenv()

SUPABASE_URL = os.environ.get(
    "SUPABASE_URL", "https://your-supabase-project.supabase.co"
)
SUPABASE_API_KEY = os.environ.get("SUPABASE_API_KEY", "YOUR_SUPABASE_API_KEY")
supabase: Client = create_client(SUPABASE_URL, SUPABASE_API_KEY)


def check_existing_feedback(entry_id: str) -> bool:
    print(f"Checking if feedback already exists for entry: {entry_id}")

    try:
        result = (
            supabase.table("feedback").select("*").eq("entry_id", entry_id).execute()
        )
    except Exception as e:
        print(f"Error checking feedback existence: {e}")
        raise HTTPException(
            status_code=500, detail=f"Error checking feedback existence: {e}"
        ) from e

    data = result.data

    if data and isinstance(data, list) and len(data) > 0:
        print(f"Feedback already exists for entry: {entry_id}")
        return True

    print(f"No existing feedback for entry: {entry_id}")
    return False


def get_ai_insight(entry_content: str) -> str:
    print("Starting AI insight generation")

    system_prompt = (
        "You are a compassionate and insightful assistant specialized in therapeutic care. "
        "Analyze the following journal entry and provide thoughtful insights that encourage reflection and growth."
    )

    messages = [
        {"role": "system", "content": system_prompt},
        {"role": "user", "content": entry_content},
    ]

    try:
        response = ollama.chat(model="llama3.2", messages=messages)
        print("AI response received")
    except Exception as e:
        print(f"Error communicating with AI service: {e}")
        raise HTTPException(
            status_code=500, detail=f"Error communicating with AI service: {e}"
        ) from e

    insight = response.get("message").get("content")

    if not insight:
        print("Invalid response from AI service (missing 'insight')")
        raise HTTPException(
            status_code=500,
            detail="Invalid response from AI service (missing 'insight').",
        )

    print("AI insight successfully extracted")
    return insight


def generate_title(entry_content: str) -> str:
    words = entry_content.split()

    if len(words) >= 5:
        title = " ".join(words[:5])
    else:
        title = entry_content.split(".")[0].strip()

    title = title.rstrip(",.!?;:").capitalize() + "..."
    return title


def insert_feedback(entry_id: str, insight: str) -> Feedback:
    print("Starting feedback insertion")
    try:
        result = (
            supabase.table("feedback")
            .insert(
                {
                    "entry_id": entry_id,
                    "content": insight,
                    "title": generate_title(insight),
                }
            )
            .execute()
        )
        print("Feedback insertion successful")
    except Exception as e:
        print(f"Error communicating with Supabase: {e}")
        raise HTTPException(
            status_code=500, detail=f"Error communicating with Supabase: {e}"
        ) from e

    data = result.data

    if not (isinstance(data, list) and len(data) > 0):
        print("Failed to insert feedback into Supabase")
        raise HTTPException(
            status_code=500, detail="Failed to insert feedback into Supabase."
        )

    print("Feedback stored successfully")
    return Feedback(**data[0])


async def analyze_and_insert_feedback(entry: Entry) -> None:
    print(f"Processing journal entry: {entry.id}")

    if check_existing_feedback(str(entry.id)):
        print(f"Feedback already exists for entry {entry.id}, skipping processing.")
        return

    insight = await run_in_threadpool(get_ai_insight, entry.content)
    await run_in_threadpool(insert_feedback, str(entry.id), insight)

    print(f"Processing complete for entry: {entry.id}")
