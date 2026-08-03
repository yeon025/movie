from sqlalchemy import Column, BigInteger, String, Text, Float, Integer, Date
from app.core.database import Base


class Movie(Base):

    __tablename__ = "movies"

    id = Column(BigInteger, primary_key=True)

    title = Column(String(255))
    synopsis = Column(Text)

    release_date = Column(Date)

    rating = Column(Float)

    runtime = Column(Integer)