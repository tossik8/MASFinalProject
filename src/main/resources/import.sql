insert into branch(id, name, address) values (1000, 'A', 'Koszykowa 86')
insert into client(id, name, surname, email, password, branch_id) values (1000, 'Mike', 'Geller', 'mgeller@gmail.com', '12345678', 1000);
insert into account(id, account_number, balance, opening_date, overdraft_limit, interest_rate, investment_objective, dtype, owner_id) values (1000, '4893443', 0, CURRENT_DATE, 100, 0, null, 'CheckingAccount', 1000);
