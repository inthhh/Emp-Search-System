# ๐ป Emp-Search-System ๐ฃ

#### ๐ JDBC๋ฅผ ํ์ฉํ ์ง์๊ฒ์ ์์คํ ํํ๋ก์ ํธ์๋๋ค.
#### (master ๋ธ๋์น๋ฅผ ์ฐธ์กฐํด ์ฃผ์ธ์.)

### [ 1๏ธโฃ ๋ฌธ์  ์ ๊ธฐ ]
- COMPANY DB์์ ์กฐ๊ฑด์ ๋ง๋ ์ง์๋ค์ ๊ฒ์, ์ถ๊ฐ, ์ญ์ , ์์ ํ  ์ ์๋ ์๋ฐ ํ๋ก๊ทธ๋จ์ ๊ตฌํํ๋ค.
- JDBC๋ฅผ ์ฌ์ฉํ์ฌ MYSQL์ ์ ๊ทผํ ํ, COMPANY DB๋ฅผ ์ฌ์ฉํ๋ค. (COMPANY DB๋ ์ด๋ฏธ ์กด์ฌํ๋ค๊ณ  ๊ฐ์ ํ๊ณ  ํ๋ก์ ํธ๋ฅผ ์งํํ๋ค.)
- ๋ค์์ ์์ฒญ์ฌํญ์ด ์ํ๊ฐ๋ฅํ๋ค.
  1. EMPLOYEE ํ์ด๋ธ์ attributes๋ค์ ๋ชจ๋ ์ถ๋ ฅํ๊ธฐ
  2. ๊ฒ์ ๋ฒ์, ๊ฒ์ ํญ๋ชฉ ํํฐ๋ฅผ ์ ์ฉํ์ฌ ํด๋น๋๋ ์ง์์ ์ ๋ณด๋ฅผ ๊ฒ์ ๋ฐ ์ถ๋ ฅํ๊ธฐ
  3. ๊ฒ์๋ ์ง์์ ์ ํํ๊ณ  DB์์ ์ญ์ ํ๊ธฐ
  4. ์๋ก์ด ์ง์ ์ ๋ณด๋ฅผ GUI์์ ์ง์  ์ถ๊ฐํ๊ธฐ
  5. ๋ถ์๋ณ ์ง์ ๋ชจ๋ ์ถ๋ ฅํ๊ธฐ
  6. ๋ถ์๋ณ ์๊ธ์ ์ผ๊ด ์์ ํ๊ธฐ

### [ 2๏ธโฃ ์คํํ๊ฒฝ ]
๊ฐ๋ฐ ํ๊ฒฝ : ์๋ฐ ๋ฒ์  openjdk 18.0.2 ๋ฐ intellij / eclipse
1.	Mysql ์๋ฒ๋ฅผ ์คํ์ํจ๋ค.
2.	/src/JDBCproject/companyDB.java ์์ ์๋ ๋ณ์๋ค์ ํ๋ก๊ทธ๋จ์ ํ์คํธํ  ํ๊ฒฝ์ mysql ์๋ฒ user name, password, database name์ผ๋ก ๋ณ๊ฒฝํด์ค๋ค.
<img width="452" alt="image" src="https://user-images.githubusercontent.com/91872300/209252966-d59545ae-4264-4eed-bef5-d53ce379cb22.png">
3.	์ดํ companyDB Class์ main ํจ์๋ฅผ ์คํ์ํจ๋ค.
4.	GUI ์ฐฝ์์ ๊ฒ์ ๋ฒํผ์ ๋๋ ์ ๋, ์ฝ์์ โ์ฐ๊ฒฐ ์ฑ๊ณตโ ๋ฉ์์ง๊ฐ ๋จ๋ฉด ์ฑ๊ณต์ ์ผ๋ก ์คํ๋ ๊ฒ์ด๋ค.

### [ 3๏ธโฃ ๊ตฌํ ๊ธฐ๋ฅ ์์ฝ ]
<img width="597" alt="image" src="https://user-images.githubusercontent.com/91872300/209253049-4c627ac4-4f7b-4526-89d1-342e8e46861c.png">
<img width="598" alt="image" src="https://user-images.githubusercontent.com/91872300/209253067-9d473e5f-58c4-4f29-be85-2803c9154b84.png">
<img width="600" alt="image" src="https://user-images.githubusercontent.com/91872300/209253096-cf98aba4-668a-45ac-bdbf-ccfee1ff4ed4.png">
<img width="615" alt="image" src="https://user-images.githubusercontent.com/91872300/209253124-cb9edfb8-f9cb-4cf8-9ae6-8f2d0c712e62.png">
<img width="570" alt="image" src="https://user-images.githubusercontent.com/91872300/209253136-00173ed7-f9ac-4beb-9889-7626dae7874a.png">
<img width="599" alt="image" src="https://user-images.githubusercontent.com/91872300/209253193-e3ec54a8-82d3-4d0e-be89-2ddb0db1e67b.png">
<img width="611" alt="image" src="https://user-images.githubusercontent.com/91872300/209253219-9b105e07-1df5-40f1-a3e9-d3331bdf9f8b.png">
