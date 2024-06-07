insert into branch(id, name, address) values (1000, 'A', 'Koszykowa 86');
insert into branch(id, name, address) values (1001, 'B', 'Koszykowa 87');

insert into client(id, name, surname, email, password, branch_id) values (1000, 'Mike', 'Geller', 'mgeller@gmail.com', '12345678', 1000);
insert into client(id, name, surname, email, password, branch_id) values (1001, 'Angelina', 'Jolie', 'ajolie@gmail.com', '123456789', 1000);
insert into client(id, name, surname, email, password, branch_id) values (1002, 'Michal', 'Jordan', 'airjordan@gmail.com', '123456789', 1001);

insert into checking_account(id, account_number, balance, opening_date, overdraft_limit, status, owner_id) values (1000, '61109010141234567891234567', 0, CURRENT_DATE, 100, 0, 1000);
insert into checking_account(id, account_number, balance, opening_date, overdraft_limit, status, owner_id) values (1001, '61109010141234567891234568', 0, '2022-08-15', 1000, 1, 1001);
insert into savings_account(id, account_number, balance, opening_date, interest_rate, status, owner_id) values (1002, '61109010141234567891234569', 0, '2023-02-02', 0.05, 0, 1001);
insert into investment_account(id, account_number, balance, opening_date, investment_objective, status, owner_id) values (1003, '61109010141234567891234570', 0, CURRENT_DATE, 'Capital gain', 0, 1001);
insert into checking_account(id, account_number, balance, opening_date, overdraft_limit, status, owner_id) values (1004, '61109010141234567891234571', 0, CURRENT_DATE, 500, 0, 1002);

insert into loan(id, interest_rate, balance, opening_date, owner_id, principal, maturity_date, dtype) values (1000, 1, 10000, CURRENT_DATE, 1000, 10000, '2025-1-1', 'TermLoan')