SELECT *
FROM item
WHERE sender @@ to_tsquery('english','donald.duck');
