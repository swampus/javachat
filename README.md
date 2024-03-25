#javachat
Short description

- Simple java chat server implementation, allows user to login, join / left rooms, send and fetch messages
  from room the are in.

- App have embedded in-memory H2 database for persist messages

- WebSocket Handler ( command pattern for processing request ) and Rest Controller
    - Join Room / Leave Room: REST
    - Fetch Message: WebSocket
    - Send Message: WebSocket
    - Delete Message: REST

- 3 Layer architecture, Stateless

#Start

- mvn spring-boot:run
  localhost:8080/h2-console
    - Database UI: user: sa <empty passsword>  localhost:8080/h2-console

#Usage
To access Rest endpoints.

- authentificate (obtain token): localhost:8080/api/v1/rest/auth

```
{
"username": "user1",
"password": "password1"
}
```

- join room: localhost:8080/api/v1/rest/chat/room/join
- left room: localhost:8080/api/v1/rest/chat/room/left

```
{
  "roomId": 1
}
```

- delete message: localhost:8080/api/v1/rest/chat/message/delete

```
{
  "messageId": 1
}
```

To access WebSocket Endpoints

npm install ws
create file for send message

```
const WebSocket = require('ws');

const ws = new WebSocket('ws://localhost:8080/chat');

ws.on('open', function open() {
console.log('WebSocket connected');
const message = JSON.stringify({
    "@class": "SendMessageRequest",
    "userId": 1,
    "roomId": 1,
    "content": "Hello world!",
    "token": "<OBTAINED TOKEN from AUTH response>"
});
ws.send(message);
});

ws.on('message', function incoming(data) {
console.log('Received message:', data);
});
```

Fetch messages:
```
const WebSocket = require('ws');

const ws = new WebSocket('ws://localhost:8080/chat');

ws.on('open', function open() {
  console.log('WebSocket connected');
  const fetchMessageRequest = JSON.stringify({
    "@class": "FetchMessagesRequest",
    "roomId": 1,
    "token": "<OBTAINED TOKEN FROM AUTH RESPONSE>"
  });
  ws.send(fetchMessageRequest);
});

ws.on('message', function incoming(data) {
  console.log('Received message:', data.toString());
});
```

#TEST case
1. Auth user1
2. Join room 1 with user1
3. Send message from user1
4. Auth user2
5. Join room 2 with user2
6. Send message from user2
7. Fetch message from user2


# users
user1 password1
user2 password2
user3 password3
