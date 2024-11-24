# Содержание

1. [Диаграмма классов](#диаграмма-классов)  
2. [Диаграмма вариантов использования и глоссарий](#диаграмма-вариантов-использования-и-глоссарий)  
3. [Диаграммы активности](#диаграммы-активности)  
4. [Диаграммы последовательности](#диаграммы-последовательности)  
5. [Диаграммы состояний](#диаграммы-состояний)  
6. [Диаграммы компонентов и развёртывания](#диаграммы-компонентов-и-развёртывания)  

---

## Диаграмма классов

Диаграмма классов отображает ключевые сущности приложения Book Tracker, их свойства, методы и взаимосвязи. Например, класс **Collection** связан с классом **Book**, отражая отношения между коллекциями и содержащимися в них книгами. [Ссылка на диаграмму классов](./classes.pdf).

Глоссарий включает описание классов, полей и т.д. Ссылка на глоссарий диаграммы вариантов использования. [Ссылка на глоссарий классов](./GlossaryClasses.md).

---

## Диаграмма вариантов использования и глоссарий

Диаграмма вариантов использования иллюстрирует функционал приложения: от создания коллекции до редактирования книг. [Ссылка на диаграмму вариантов использования](./use-case.pdf).  
Глоссарий включает описание терминов, таких как "коллекция", "книга" и "поиск", для унификации понятий. [Ссылка на глоссарий диаграммы вариантов использования](./GlossaryUseCase.md).

---

## Диаграммы активности

### Основные процессы:  
- [Создание коллекции](./active/create-collection.png)  
- [Добавление книги](./active/add-book.png)  
- [Редактирование книги](./active/edit-book.png)  

Эти диаграммы отображают шаги пользователя в типичных сценариях.

---

## Диаграммы последовательности

- [Просмотр коллекции](./sequence/view-collection.png)  
- [Поиск книги](./sequence/search-book.png)  
- [Обновление прогресса чтения](./sequence/update-progress.png)  

Диаграммы последовательности показывают взаимодействие пользователя с системой в рамках функциональных сценариев.

---

## Диаграммы состояний

- [Добавление книги](./status/add-book1.png): добавление книги в коллекцию,  ввод названия
- [Добавление коллекции](./status/add-collection1.png): добавление коллекции, ввод названия 

---

## Диаграммы компонентов и развёртывания

Диаграммы демонстрируют архитектуру приложения, включая модульные зависимости (например, "База данных" ↔ "UI"), и отображают инфраструктуру развёртывания. [Ссылка на диаграммы компонентов](./components.pdf).  
