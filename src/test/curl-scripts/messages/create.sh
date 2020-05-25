#!/bin/bash

echo $FROM A
echo $TO A
echo $MESSAGE A
curl "http://localhost:8080/messages" \
  --include \
  --request POST \
  --header "Content-Type: application/json" \
  --data '{
      "fromNickname": "'"${FROM}"'",
      "toNickname": "'"${TO}"'",
      "messageText": "'"${MESSAGE}"'"
  }'

echo
