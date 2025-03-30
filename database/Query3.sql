--Average room price by capacity
SELECT capacity,
       AVG(price) AS avg_price
  FROM Room
 GROUP BY capacity
 ORDER BY capacity;