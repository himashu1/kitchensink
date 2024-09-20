

# kitchensink # To Migrate from EAP to Spring Boot and Java 21
# Approach / Steps for migration
# 1 - Identify All external dependencies and EAP specific dependencies.
# 2 - Find EAP specific dependencies and replace them with Spring spaecific dependecies otherwise if not neccessary, remove those dependecies.
# 3 - Refactor Models / Table specific classes. Here main model class is member model. Replace Annotations specific for EAP with Spring JPA Based annotations.
# 4 - Redefine Service layer as per Spring JPA.
# 5 - Refactor RestController and use Spring Web specific annotations.
# 6 - Refactor Member Repository for DB operations.