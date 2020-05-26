#!/bin/bash

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
