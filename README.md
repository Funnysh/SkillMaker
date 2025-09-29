# Skill Maker - Webová aplikace na učení skillů

Osobní projekt vyvinutý v Javě, Spring Bootu a Reactu.
Cílem aplikace je pomoci uživatelům efektivně se učit nové dovednosti – mohou si zadat skill, který chtějí ovládnout, a v budoucnu jim aplikace pomocí AI vygeneruje personalizovaný plán, který si mohou postupně odškrtávat. Uživatel tak postupuje na vyšší úroveň a sbírá zkušenosti formou gamifikace.

## Funkcionalita 
-✅ Registrace a login uživatele
-✅ JWT autentizace a autorizace
-✅ CRUD operace
-✅ Správa profilu
-🔄 Další rozpracované funkce: správa skillů

## Použité technologie
- **Backend:** Java, Spring Boot, Spring Security, JWT, Maven
- **Frontend:** React
- **Databáze:** MySQL
- **Nástroje:** Git, REST API, Postman

## Jak projekt spustit
bash
git clone https://github.com/Funnysh/SkillMaker.git

cd skillmaker-backend
./mvnw spring-boot:run

cd skillmaker-frontend
npm install
npm start

## Stav projektu
Projekt je stále ve vývoji.
Hlavním cílem je vybudovat plnohodnotnou aplikaci napojenou na AI, která uživatelům vytvoří personalizované tréninkové plány a gamifikovanou cestu k jejich dosažení.

### Roadmap
- [x] Registrace a login uživatele
- [x] JWT autentizace
- [x] CRUD operace pro uživatele
- [x] CRUD operace pro habity
- [x] CRUD operace pro skilly
- [ ] AI generátor tréninkového plánu
- [ ] Gamifikace (XP systém, odměny, ...)