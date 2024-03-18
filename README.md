# simpleBankSystem
Simple bank system that is capable of:
1. Register an account with account number, auto generated branch number, client, balance, limit, and account type
2. Deposit to created accounts
3. Withdraw from created accounts
4. Change limit (only for accounts created with limit)
5. Transfer money (at this point it only allow internal transfers where both accounts are already created)
6. Transfer have a limited amount related to the time of the day (fixed directly by the system)
7. Export transfer history to a csv file.

All the operations are selected using a menu in terminal

Future possibly improvements:
1. Possibility for the user to select the amount limit and time for transfers
2. Change the menu schema to work with a state machine instead of using switch/case
3. Include other types of transactions
4. Include other party data like addresses, or more phone numbers etc.
5. Include specific input validation for data like phone, email etc.

Bellow is the UML diagram for this system:
![Alt text](src/uml/uml.svg?raw=true "UML Diagram")
