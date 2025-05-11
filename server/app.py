from fastapi import FastAPI
from routes import router as analysis_router

app = FastAPI(
    title="Therapeutic Journal Analysis API",
    description="Accepts journal entry data, generates AI insights via Ollama, and stores feedback in Supabase.",
    version="1.0.0"
)

app.include_router(analysis_router, prefix="/api")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("app:app", host="0.0.0.0", port=8000, reload=True)
