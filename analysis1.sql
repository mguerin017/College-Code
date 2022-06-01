CREATE VIEW percent_attendees
AS
SELECT event_id as eid, ROUND((COUNT(*) * 1.0 / (SELECT COUNT(*) FROM attending)) * 100, 2) as percent_attended
	FROM attending
	GROUP BY event_id;

WITH 'event' (event) AS (SELECT event_id from attending WHERE event_id = 1010 OR event_id = 1011),
	'percentage' (percentage) AS (SELECT((COUNT(attending.comp_id) * 1.0) / (SELECT COUNT(competitors.comp_id) from competitors)) * 100 FROM attending)
	SELECT ROUND(

SELECT * FROM attending;
SELECT * FROM competitors;
SELECT event_id as eid, ((COUNT(attending.comp_id) * 1.0) / (SELECT COUNT(competitors.comp_id) from competitors)) * 100 as percent_attended
	FROM attending
	INNER JOIN competitors
	ON attending.comp_id = competitors.comp_id
	WHERE event_id = 1010 OR event_id = 1011
	GROUP BY event_id;
SELECT * FROM percent_attendees;