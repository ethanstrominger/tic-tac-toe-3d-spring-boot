#Messages

##Unit Tests
- Create a new message with from, to, message.
- Retrieve all messages from a specific user
- Retrieve all messages to a specific user
- Retrieve all messages from or to a specific user
- Add user to list of users on system

##Persistence Test
- Session: Messages added in one session can be seen in another session
- Server: Messages added in one session can be seen when machine is turned off

#Notification Test
- New message is sent to user logged in different session based on to

#Logged On User List

##Unit tests
- Create a new logged on user with username
- List all logged on users includes the created users
- Remove user from user list

##Persistence Tests
- Persists across sessions
- Does not persist across servers

##Notification
- Everyone is notified when new user logs on

#Games