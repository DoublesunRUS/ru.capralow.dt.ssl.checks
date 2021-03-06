#Область СлужебныйПрограммныйИнтерфейс

// Определяет список объектов конфигурации, в модулях менеджеров которых предусмотрена процедура 
// ДобавитьКомандыЗаполнения, формирующая команды заполнения объектов.
// Синтаксис процедуры ДобавитьКомандыЗаполнения см. в документации.
//
// Параметры:
//   Объекты - Массив - объекты метаданных (ОбъектМетаданных) с командами заполнения.
//
// Пример:
//	Объекты.Добавить(Метаданные.Справочники.Организации);
//   
Процедура ОпределитьОбъектыСКомандамиЗаполнения(Объекты) Экспорт

	Объекты.Добавить(Метаданные.Документы.ЗаполнениеБезПроцедур);

КонецПроцедуры

#КонецОбласти