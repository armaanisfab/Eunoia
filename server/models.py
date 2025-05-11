from pydantic import BaseModel, Field, field_validator
from uuid import UUID
from datetime import datetime
from typing import Optional


class BaseDBModel(BaseModel):
    id: Optional[UUID] = Field(default=None)
    created_at: Optional[datetime] = Field(default=None)

    @field_validator("created_at", mode="before")
    def convert_datetime(cls, value):
        if isinstance(value, str):
            return datetime.fromisoformat(value.replace(" +00", ""))
        return value


class Entry(BaseDBModel):
    content: str
    journal_id: UUID


class Feedback(BaseDBModel):
    entry_id: UUID
    content: str


class FeedbackCreate(BaseModel):
    entry_id: UUID
    content: str
