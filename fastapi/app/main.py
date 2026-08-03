from fastapi import FastAPI
import logging

from app.routers.rag import router as rag_router


logging.getLogger("uvicorn").disabled = True
# logging.getLogger("uvicorn.error").disabled = True
logging.getLogger("uvicorn.access").disabled = True


app = FastAPI()

app.include_router(rag_router)