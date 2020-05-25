#!/bin/bash

curl "http://localhost:4741/calendars" \
  --include \
  --request GET \
  --header "Authorization: Token token=${TOKEN}"

echo
