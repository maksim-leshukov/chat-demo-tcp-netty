Нужно создать многопользовательский консольный чат (без GUI). Все пользователи чата будут находиться в едином пространстве и будут видеть сообщения от других пользователей. Помимо отправки обычных текстовых сообщений, пользователи могут отправлять команды на сервер (например, для получения количества подключенных клиентов или для изменения текущего имени пользователя). Должна быть команда help, которая возвращает информацию по другим командам с их описанием.


Примерный алгоритм поведения пользователя:
Каждый клиент при входе вводит свое имя. Если имя уже занято, то клиент должен ввести другое имя.
После аутентификации клиент получает последние 100 сообщений с сервера.
Клиент может ввести сообщение, при отправке оно рассылается всем другим подключенным клиентам от его имени.
Клиент может отправить на сервер произвольную команду, и в ответ получить результат выполнения.
Основные требования:
Гибкая архитектура с возможностью замены компонентов системы, либо добавления их. Также нужно иметь возможность быстро и просто добавлять новые пользовательские команды.
Сервер должен выдерживать свыше 1000 одновременных клиентов. 1к пользователей - весьма условное значение. Основная мысль заключается в том, что нужно придумать архитектуру, которая бы выдерживала как можно больше пользовательских сессий.
Для взаимодействия клиента и сервера использовать TCP сокеты, но архитектура должна позволить заменить транспортный уровень на другой(например, на http).
Для хранения сообщений использовать оперативную память. Сообщения на сервере хранятся в течении текущей сессии, соответственно, нет нужды сохранять эту информацию во внешних хранилищах.
Unit тесты. Чат должен быть покрыт юнит-тестами (именно юнит тестами, а не интеграционными).
Нагрузочное тестирование. Для этой цели можно написать ботов, на какой технологии это делать — решать тебе.
Нельзя использовать фреймворки или библиотеки которые решают задачу на 80%, но можно использовать библиотеки, которые в целом облегчают разработку, например Google Guava, Apache Commons и другие.
Нельзя использовать telnet в виде клиента или нечто подобное.


Для сборки проекта нужно использовать Maven. Основное требование к результату — возможность быстро запустить результат тестового задания без исправления кода.
Один из главных параметров при оценке тестового задания — выбранная архитектура и ее реализация. В идеале архитектура должна позволять нам изменить один из компонентов системы не трогая другие, например, сетевой протокол взаимодействия клиента и сервера. Также обращается внимание на общий стиль кода, наименование методов, классов, на их размер и суть.


В результате выполнения задания мы должны получить законченный чат, сервер которого можно отправить на боевые сервера, а клиент раздать пользователям.