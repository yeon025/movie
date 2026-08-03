from sqlalchemy.orm import Session

from app.models.movie import Movie
from app.services.prompt_service import generate_gpt_answer


def generate_answer(
    db: Session,
    query: str
):

    movies = (
        db.query(Movie)
        .order_by(Movie.rating.desc())
        .limit(10)
        .all()
    )


    context = "\n".join(
        [
            f"""
제목: {movie.title}
줄거리: {movie.synopsis}
평점: {movie.rating}
상영시간: {movie.runtime}분
"""
            for movie in movies
        ]
    )


    return generate_gpt_answer(
        query,
        context
    )