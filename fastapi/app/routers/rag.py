from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session

from app.schemas.rag import RagRequest, RagResponse
from app.core.database import get_db
from app.services import rag_service


router = APIRouter(
    prefix="/api/movies/rag",
    tags=["rag"]
)


@router.post("/explanation")
def explanation(
    request: RagRequest,
    db: Session = Depends(get_db)
):

    answer = rag_service.generate_answer(
        db,
        request.query
    )

    return RagResponse(
        answer=answer
    )