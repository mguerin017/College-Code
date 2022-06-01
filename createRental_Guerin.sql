CREATE TABLE IF NOT EXISTS movies (
  movie_id INTEGER PRIMARY KEY,
  title VARCHAR NOT NULL,
  genre VARCHAR
);

CREATE TABLE IF NOT EXISTS salutations (
    sal_id INTEGER PRIMARY KEY,
    title VARCHAR NOT NULL,
    prepnoun VARCHAR
);

create table if not exists members (
    mem_id INTEGER PRIMARY KEY,
    mem_name VARCHAR NOT NULL,
    mem_address VARCHAR,
	mem_city VARCHAR,
	mem_state VARCHAR,
	mem_zip INTEGER,
    sal_id INTEGER NOT NULL,
    mem_phone INTEGER NOT NULL,
    mem_email VARCHAR NOT NULL,
    verified BOOLEAN DEFAULT 0,
    FOREIGN KEY (sal_id) REFERENCES salutations(sal_id)
);

CREATE TABLE IF NOT EXISTS rentals (
    rental_id INTEGER PRIMARY KEY,
    mem_id INTEGER NOT NULL,
    movie_id INTEGER NOT NULL,
    rental_length INTEGER NOT NULL,
    FOREIGN KEY (mem_id) REFERENCES members(mem_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);

INSERT INTO movies VALUES (1, "The long goodbye", "drama"),
(2, "Avengers: Endgame", "action"),
(3, "Doctor Who: Season 5", "scifi"),
(4, "The Notebook", "romance"),
(5, "Mulan", "family"),
(6, "Minions", "family"),
(7, "Star Wars: A New Hope", "scifi"),
(8, "Star Wars: The Empire Strikes Back", "scifi"),
(9, "24", "drama"),
(10, "DeadPool", "action"),
(11, "50 First Dates", "romance");

insert into salutations values (101,"Mr", "he"),
(102, "Mrs", "she"),
(103, "Ms", "she"),
(104, "Dr", "ind"),
(105, "Mx", "zis"),
(106, "Ind", "ind");

-- 1 = "verified"
insert into members values (2001, "John Small", "123 N Street", "Fort Myers", "FL", 33901, 101, 1234567891, "john.s@foo.edu", 1),
(2002, "Jane Smith", "1389 S Way", "Naples", "FL", 34109, 102, 2178899878, "jane@smith.com", 1),
(2003, "Chris Janeson", "2255 Seashore", "Fort Myers", "FL", 33906, 106, 2398899878, "c.j@cjco.com", 0),
(2004, "Mike Doctorman", "4444 N Way", "Naples", "FL", 34109, 101, 2395559878, "doctorman@g.com", 0),
(2005, "Sue Doctorman", "4444 N Way", "Naples", "FL", 34109, 102, 2395551222, "doctorman@g.com", 0),
(2006, "Zu George", "4400 No Way St", "Estero", "FL", 34133, 105, 2515559878, "changeme@rentals.com", 0);

INSERT INTO rentals VALUES (1388, 2006, 1, 3),
(1389, 2006, 2, 3),
(1390, 2005, 3, 7),
(1391, 2002, 4, 3),
(1392, 2001, 5, 5),
(1393, 2001, 6, 5),
(1394, 2004, 7, 3),
(1395, 2004, 8, 3),
(1396, 2004, 4, 3),
(1397, 2003, 9, 7),
(1398, 2003, 10, 5),
(1399, 2005, 11, 5);

SELECT * FROM salutations;

SELECT * FROM members;

SELECT * FROM movies;

SELECT * FROM rentals;


-- There appears to be no way to have the query only select the first instance of a rental by each person.
-- SELECT DISTINCT appears to only select distinct combinations of all columns (of course, all of the column combinations are distinct, so it returns every one)
SELECT salutations.title, members.mem_name, movies.title, movies.genre, members.mem_email from rentals
	INNER JOIN members ON members.mem_id = rentals.mem_id
	INNER JOIN salutations ON salutations.sal_id = members.sal_id
	INNER JOIN movies ON movies.movie_id = rentals.movie_id;

SELECT movies.title as movies_rented_by_2003_2004 FROM rentals
	INNER JOIN movies ON movies.movie_id = rentals.movie_id
	WHERE mem_id = 2003 OR mem_id = 2004;

SELECT COUNT(rental_id) AS num_rented_by_2003_2004 FROM rentals
	WHERE mem_id = 2003 OR mem_id = 2004;

SELECT COUNT(*) AS num_scifi_or_family FROM rentals
	INNER JOIN movies ON movies.movie_id = rentals.movie_id
	WHERE movies.genre = "scifi" OR movies.genre = "family";

SELECT movies.genre, COUNT(*) FROM rentals
	INNER JOIN movies ON movies.movie_id = rentals.movie_id
	GROUP BY movies.genre;

SELECT members.mem_city, COUNT(*) FROM rentals
	INNER JOIN members ON members.mem_id = rentals.mem_id
	GROUP BY members.mem_city;