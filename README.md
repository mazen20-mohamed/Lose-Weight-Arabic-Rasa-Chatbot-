# Lose-Weight-Arabic-Rasa-Chatbot-
Lose Weight Arabic Rasa Chatbot 



- To Train your project use command:
      rasa train
      
- To Talk your assistant use command:
      rasa shell
      
- To run server use command:
       rasa run --enable-api --port 5005   
     
- To test on postman example (POST ):
      localhost:5005/webhooks/rest/webhook
      and put in josn body { 
           "sender":"NoOne",
           "message":"اهلا"
      }
      
      ![run](https://user-images.githubusercontent.com/84911813/209441115-03b8901e-b9d0-4de3-a330-42851ce85e3c.png)
