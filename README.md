# Booker - appointment booking app
Appointment format for api is "yyyy-MM-dd HH:mm"

# Swagger doc
http://localhost:8080/swagger-ui.html

# H2 db login under:
http://localhost:8080/console
jdbc url: http://localhost:8080/console
login: sa
pass: sa

# EXAMPLES:

add stylist to db:
curl -v -X POST -H "Content-Type: application/json" -d '{"forename": "Janusz", "surname": "Hund", "email": "janek@wp.pl"}' http://localhost:8080/stylist

get free spots for the 2 das:
curl -v http://localhost:8080/appointment/free?days=2

book the appointment:
curl -v -X POST -H "Content-Type: application/json" -d '{"startTime": "2019-02-12 12:30","customerId":"Jan"}' http://localhost:8080/appointment