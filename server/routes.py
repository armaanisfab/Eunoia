from fastapi import APIRouter, BackgroundTasks
from models import Entry
from repository import analyze_and_insert_feedback

router = APIRouter()


@router.post("/submit", summary="Submit a journal entry for processing")
async def submit_entry(entry: Entry, background_tasks: BackgroundTasks):
    background_tasks.add_task(analyze_and_insert_feedback, entry)
    return {
        "message": "Journal entry submitted. The feedback will be processed shortly.",
        "success": True,
    }
