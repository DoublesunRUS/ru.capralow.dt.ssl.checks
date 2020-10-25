# Проверка внедрения БСП [![Build Status](https://travis-ci.com/DoublesunRUS/ru.capralow.dt.ssl.checks.svg)](https://travis-ci.com/DoublesunRUS/ru.capralow.dt.ssl.checks) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=DoublesunRUS_ru.capralow.dt.ssl.checks&metric=alert_status)](https://sonarcloud.io/dashboard?id=DoublesunRUS_ru.capralow.dt.ssl.checks) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=DoublesunRUS_ru.capralow.dt.ssl.checks&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=DoublesunRUS_ru.capralow.dt.ssl.checks) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=DoublesunRUS_ru.capralow.dt.ssl.checks&metric=coverage)](https://sonarcloud.io/dashboard?id=DoublesunRUS_ru.capralow.dt.ssl.checks)


## Проверка внедрения БСП для [1C:Enterprise Development Tools](http://v8.1c.ru/overview/IDE/) 2020.6

Минимальная версия EDT: 2020.6

Текущий релиз в ветке [master: 1.0.0](https://github.com/DoublesunRUS/ru.capralow.dt.ssl.checks/tree/master).<br>

В данном репозитории хранятся только исходники.<br>

Плагин можно установить в EDT через пункт "Установить новое ПО" указав сайт обновления http://capralow.ru/edt/unit.ssl.checks/latest/ . Для установки может потребоваться запуск EDT под правами администратора.<br>
Для самостоятельной сборки плагина необходимо иметь доступ к сайту https://releases.1c.ru и настроить соответствующим образом Maven. Подробности настройки написаны [здесь](https://github.com/1C-Company/dt-example-plugins/blob/master/simple-plugin/README.md).

### Возможности
Плагин добавляет проверки, аналогичные обработке "Проверка внедрения БСП" в той части, которая занимается проверкой модулей.<br>
Плагин является заготовкой и не предназначен для использования в реальной разработке.

### Подключаемые команды
Ключевые версии БСП: 2.4.1, 3.1.1, 3.1.2<br>
В формах объектов и списков проверяется: наличие подключаемых процедур подсистемы; наличие вызовов процедур подсистемы из событий формы.<br>
Проверяется что объект у которого есть вызов в форме объекта или списка подключен к одной из подсистем "Варианты отчетов", "Дополнительные отчеты и обработки", "Заполнение объектов", "Печать".