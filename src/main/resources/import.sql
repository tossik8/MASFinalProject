insert into branch(id, name, address) values (1000, 'A', 'Koszykowa 86')
insert into client(id, name, surname, email, password, branch_id) values (1000, 'Mike', 'Geller', 'mgeller@gmail.com', '12345678', 1000);
insert into checking_account(id, account_number, balance, opening_date, overdraft_limit, owner_id) values (1000, '4893443', 0, CURRENT_DATE, 100, 1000);
