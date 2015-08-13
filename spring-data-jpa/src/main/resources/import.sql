
insert into library (id, name) values (13, 'MBP filia nr 1');
insert into library (id, name) values (14, 'MBP filia nr 2');
insert into library (id, name) values (15, 'Biblioteka PWr filia nr 1');

insert into book (id, title, library_id) values (1, 'Pierwsza książka',13);
insert into book (id, title, library_id) values (2, 'Druga książka',13);
insert into book (id, title, library_id) values (3, 'Trzecia książka',14);

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);
