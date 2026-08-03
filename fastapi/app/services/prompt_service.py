from openai import OpenAI
import os


api_key = os.getenv("OPENAI_API_KEY")

if not api_key:
    raise RuntimeError("OPENAI_API_KEY가 설정되지 않았습니다.")

client = OpenAI(
    api_key=os.getenv("OPENAI_API_KEY")
)

def generate_gpt_answer(query, context):

    prompt = f"""
너는 영화 추천 AI다.

사용자 요청:
{query}

영화 데이터:
{context}

위 영화 중 적합한 작품을 추천하고
추천 이유를 설명해줘.
"""


    response = client.chat.completions.create(
        model="gpt-4.1-mini",
        messages=[
            {
                "role": "system",
                "content": "너는 영화 추천 전문가다."
            },
            {
                "role": "user",
                "content": prompt
            }
        ]
    )

    return response.choices[0].message.content