Example end point
curl --location --request POST 'http://localhost:8081/api/order' \
--header 'Content-Type: application/json' \
--data-raw '{
"orderLineItemsDtoList": [   
{
"skuCode":"gun_1",
"price": 10000.0,
"quantity": 1
}
]

}'

Run MySQL image
docker run -d --name mysql-container -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_USER=myuser -e MYSQL_PASSWORD=mypassword -e MYSQL_DATABASE=order-service -p 3307:3306 mysql:latest

Run phpMyadmin
docker run -d --name phpmyadmin-container -e PMA_ARBITRARY=1 -p 8082:80 --link mysql-container:db --platform linux/amd64 phpmyadmin/phpmyadmin:latest