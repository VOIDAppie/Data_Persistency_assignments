-- ------------------------------------------------------------------------
-- Data & Persistency
-- Opdracht S7: Indexen
--
-- (c) 2020 Hogeschool Utrecht
-- Tijmen Muller (tijmen.muller@hu.nl)
-- André Donk (andre.donk@hu.nl)
-- ------------------------------------------------------------------------
-- LET OP, zoals in de opdracht op Canvas ook gezegd kun je informatie over
-- het query plan vinden op: https://www.postgresql.org/docs/current/using-explain.html


-- S7.1.
-- 1. 
Gather  (cost=1000.00..6152.27 rows=1010 width=96)
  Workers Planned: 2
    ->  Parallel Seq Scan on order_lines  (cost=0.00..5051.27 rows=421 width=96)
            Filter: (stock_item_id = 9)

-- 3.
Bitmap Heap Scan on order_lines  (cost=12.12..2305.84 rows=1010 width=96)
  Recheck Cond: (stock_item_id = 9)
    ->  Bitmap Index Scan on ord_lines_si_id_idx  (cost=0.00..11.87 rows=1010 width=0)
            Index Cond: (stock_item_id = 9)

-- 4.
Het verschil is dat met de index op stock_item_id hij niet langs iedere row gaat maar doormiddel van een bitmap index scan zoekt naar de index.


-- S7.2.
-- 1 & 2. 
-- 	  A.
Index Scan using pk_sales_orders on orders  (cost=0.29..8.31 rows=1 width=155)
  Index Cond: (order_id = 73590)

-- 	  B. 
Seq Scan on orders  (cost=0.00..1819.94 rows=107 width=155)
  Filter: (customer_id = 1028)

-- 3. 
Bij het zoeken naar de order doormiddel van het order_id maak je gebruik van de primary key waardoor je geen sequence scan doet maar bij de customer_id gebeurt dat wel.

-- 4. 
CREATE INDEX ord_cm_id ON orders (customer_id);

-- 5.
Bitmap Heap Scan on orders  (cost=5.12..308.96 rows=107 width=155)
  Recheck Cond: (customer_id = 1028)
    ->  Bitmap Index Scan on ord_cm_id  (cost=0.00..5.10 rows=107 width=0)
            Index Cond: (customer_id = 1028)

-- 6.
Door de toegevoegde index op customer_id maakt hij nu gebruik van een bitmap index scan in plaats van een sequence scan.


-- S7.3.A
select orders.order_id, orders.order_date, orders.salesperson_person_id as verkoper, 
	(orders.expected_delivery_date - orders.order_date) as levertijd, order_lines.quantity from orders
	join order_lines on orders.order_id = order_lines.order_id 
	where orders.salesperson_person_id in (select orders.salesperson_person_id from orders 
	group by orders.salesperson_person_id 
	having (avg(orders.expected_delivery_date - orders.order_date)) > 1.45) and order_lines.quantity > 250
	order by levertijd desc, verkoper;



-- S7.3.B
-- 1.
Gather Merge  (cost=9713.39..9740.23 rows=230 width=20)
  Workers Planned: 2
  ->  Sort  (cost=8713.37..8713.66 rows=115 width=20)
        Sort Key: ((orders.expected_delivery_date - orders.order_date)) DESC, orders.salesperson_person_id
        ->  Hash Join  (cost=2188.42..8709.43 rows=115 width=20)
              Hash Cond: (orders.salesperson_person_id = orders_1.salesperson_person_id)
              ->  Nested Loop  (cost=0.29..6519.59 rows=382 width=20)
                    ->  Parallel Seq Scan on order_lines  (cost=0.00..5051.27 rows=382 width=8)
                          Filter: (quantity > 250)
                    ->  Index Scan using pk_sales_orders on orders  (cost=0.29..3.84 rows=1 width=16)
                          Index Cond: (order_id = order_lines.order_id)
              ->  Hash  (cost=2188.09..2188.09 rows=3 width=4)
                    ->  HashAggregate  (cost=2187.91..2188.06 rows=3 width=4)
                          Group Key: orders_1.salesperson_person_id
                          Filter: (avg((orders_1.expected_delivery_date - orders_1.order_date)) > 1.45)
                          ->  Seq Scan on orders orders_1  (cost=0.00..1635.95 rows=73595 width=12)

-- 2.
CREATE INDEX ord_q_id ON order_lines (quantity);

-- 3.
Sort  (cost=6459.70..6460.39 rows=275 width=20)
  Sort Key: ((orders.expected_delivery_date - orders.order_date)) DESC, orders.salesperson_person_id
  ->  Hash Join  (cost=4368.52..6448.56 rows=275 width=20)
        Hash Cond: (orders.order_id = order_lines.order_id)
        ->  Hash Join  (cost=2188.13..4099.15 rows=22078 width=16)
              Hash Cond: (orders.salesperson_person_id = orders_1.salesperson_person_id)
              ->  Seq Scan on orders  (cost=0.00..1635.95 rows=73595 width=16)
              ->  Hash  (cost=2188.09..2188.09 rows=3 width=4)
                    ->  HashAggregate  (cost=2187.91..2188.06 rows=3 width=4)
                          Group Key: orders_1.salesperson_person_id
                          Filter: (avg((orders_1.expected_delivery_date - orders_1.order_date)) > 1.45)
                          ->  Seq Scan on orders orders_1  (cost=0.00..1635.95 rows=73595 width=12)
        ->  Hash  (cost=2168.91..2168.91 rows=918 width=8)
              ->  Bitmap Heap Scan on order_lines  (cost=11.41..2168.91 rows=918 width=8)
                    Recheck Cond: (quantity > 250)
                    ->  Bitmap Index Scan on ord_q_id  (cost=0.00..11.18 rows=918 width=0)
                          Index Cond: (quantity > 250)

-- 4.
Het verschil is dat hij nu voor de quantity geen sequence scan meer doet waardoor het hele process sneller is.



-- S7.3.C
De query zou sneller kunnen door geen subquery te gebruiken. Maar door een JOIN te gebruiken


