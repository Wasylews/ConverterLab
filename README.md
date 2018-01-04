ConverterLab
============
Simple application that displays exchange rates from [finance.ua](finance.ua)

#### Architecture
This app is based on Clean Architecture by Robert Martin, so there are base modules:
- domain - use cases, repository interface, model classes;  
- data - REST adapter, repository implementation, db cache, model mappers;  
- device - device-specific stuff like PreferenceManager etc;  
- app - presenters, activities.