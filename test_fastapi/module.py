#Step1 패키지 불러오기
import torch
from transformers import AutoTokenizer, AutoModelForSequenceClassification, TextClassificationPipeline

# #Step2 추론기 만들기
# tokenizer = AutoTokenizer.from_pretrained("jaehyeong/koelectra-base-v3-generalized-sentiment-analysis")
# model = AutoModelForSequenceClassification.from_pretrained("jaehyeong/koelectra-base-v3-generalized-sentiment-analysis")
# sentiment_classifier = TextClassificationPipeline(tokenizer=tokenizer, model=model)

# #Step3 데이터 입력
# review = '이쁘고 좋아요~~~씻기도 편하고 아이고 이쁘다고 자기방에 갖다놓고 잘써요~^^'

# #Step4 추론하기
# result = sentiment_classifier(review)

# #Step5 결과 도출하기
# print(f'{review}\n>> {result[0]}')


def text_classification(review):
    #Step2 추론기 만들기
    tokenizer = AutoTokenizer.from_pretrained("jaehyeong/koelectra-base-v3-generalized-sentiment-analysis")
    model = AutoModelForSequenceClassification.from_pretrained("jaehyeong/koelectra-base-v3-generalized-sentiment-analysis")
    sentiment_classifier = TextClassificationPipeline(tokenizer=tokenizer, model=model)

    #Step3 데이터 입력
    #받아오기

    #Step4 추론하기
    result = sentiment_classifier(review)
    print(review)

    #Step5 결과 도출하기
    print(result)
    return {"result" : result[0]}