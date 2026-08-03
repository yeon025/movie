from sqlalchemy import text


class MovieRepository:

    def search_similar_movies(self, db, embedding: list[float], limit: int = 10):

        query = text("""
            SELECT
                id,
                1 - (embedding <=> :embedding) AS score
            FROM movies
            WHERE embedding IS NOT NULL
            ORDER BY embedding <=> :embedding
            LIMIT :limit
        """)

        result = db.execute(query,
            {
                "embedding": embedding,
                "limit": limit
            }
        )

        return result.fetchall()