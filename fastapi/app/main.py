from fastapi import FastAPI
import logging


logging.getLogger("uvicorn").disabled = True
# logging.getLogger("uvicorn.error").disabled = True
logging.getLogger("uvicorn.access").disabled = True


app = FastAPI()
