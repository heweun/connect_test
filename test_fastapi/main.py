from fastapi import FastAPI, Form
from fastapi.middleware.cors import CORSMiddleware
from module import text_classification

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.post("/classification")
async def text_class(review: str = Form(...)):
    print(f"review: {review}")
    
    # 추론 수행
    result = text_classification(review)
    print(result)
    return result

#fastapi dev main.py --port 8007

#pip freeze > requirements.txt
#pip install -r requirements.txt