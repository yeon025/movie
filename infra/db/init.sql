CREATE EXTENSION IF NOT EXISTS vector;
 
CREATE TABLE genres (
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    VARCHAR(20) NOT NULL
);
 
CREATE TABLE users (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(20) NOT NULL,
    email       VARCHAR(100) NULL UNIQUE,
    password    VARCHAR(255) NULL,
    role        VARCHAR(20) NOT NULL,
    provider    VARCHAR(20) NULL,
    social_id   VARCHAR(100) NULL UNIQUE
);
COMMENT ON COLUMN users.role IS 'USER/ADMIN';
COMMENT ON COLUMN users.provider IS 'LOCAL/KAKAO';
 
CREATE TABLE movies (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name                VARCHAR(100) NOT NULL,
    running_time        INT NOT NULL,
    director            VARCHAR(50) NOT NULL,
    release             DATE NOT NULL,
    synopsis            TEXT NOT NULL,
    synopsis_vector     VECTOR(1536) NOT NULL
);
 
CREATE TABLE movie_genres (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    genre_id    BIGINT NOT NULL,
    movie_id    BIGINT NOT NULL,
    CONSTRAINT fk_movie_genres_genre FOREIGN KEY (genre_id) REFERENCES genres(id),
    CONSTRAINT fk_movie_genres_movie FOREIGN KEY (movie_id) REFERENCES movies(id),
    CONSTRAINT uq_movie_genres UNIQUE (movie_id, genre_id)
);
 
CREATE TABLE feedbacks (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    status      VARCHAR(20) NOT NULL,
    user_id     BIGINT NOT NULL,
    movie_id    BIGINT NOT NULL,
    CONSTRAINT fk_feedbacks_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_feedbacks_movie FOREIGN KEY (movie_id) REFERENCES movies(id),
    CONSTRAINT uq_feedbacks UNIQUE (user_id, movie_id)
);
COMMENT ON COLUMN feedbacks.status IS 'LIKE/DISLIKE/NONE';
 
CREATE INDEX idx_movies_synopsis_vector ON movies
    USING hnsw (synopsis_vector vector_cosine_ops);