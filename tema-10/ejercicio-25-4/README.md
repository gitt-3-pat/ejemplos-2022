

```sql
SELECT * FROM DOCUMENT
SELECT * FROM USER
SELECT U.USER_ID, U.USER, D.DOC_ID, D.DOC FROM DOCUMENT D, USER U WHERE U.USER_ID = D.USER_ID
```

```
curl http://localhost:8080/api/v1/users/documents
```
