## **Patron User Guide**

## **1\. Starting and logging in / registering**

1. Launch the application so that the **Patron Login** window appears.  
2. To **register a new patron**:  
   * Enter a username in the first text field and a password in the second.  
   * Click **Register**.  
   * If either field is empty, a message tells you to enter both username and password.  
   * If a patron with the same username/password already exists, a message tells you to use Login instead.  
   * On success, a new Patron (with a max checkout count of 3 and an initial fine amount) is created and added to the library’s list.  
3. To **log in as an existing patron**:  
   * Enter your username and password and click **Login**.  
   * If either field is empty, you are prompted to fill both.  
   * If no matching patron is found, you are asked to complete registration first.  
   * On success, the **Patron Menu** window opens and the login window closes.  
4. To **switch to the librarian side**, click **Switch to Librarian Login**, which opens the librarian login window and closes the patron login.

## **2\. Patron Menu – searching and viewing books**

The Patron Menu shows: a list of book titles, a search text field, and buttons for **Check In**, **Check Out**, **Pay Fines**, **Search**, and **Back**.

* The list initially shows the titles of all books on all shelves in the library.  
* To **search by title**:  
  * Type part or all of a book title into the search field.  
  * Click **Search**.  
  * The list is cleared and repopulated with titles whose (lower‑cased) titles contain your query.  
  * If the query is empty, the full list is restored.

## **3\. Patron Menu – checking books in and out**

**Checking in a book (returning):**

1. Select a title from the list that you currently have checked out.  
2. Click **Check In**.  
   * If nothing is selected, you are prompted to select a book.  
   * The program searches your personal list of checked‑out books to find the title.  
   * If found, the book is marked available and removed from your list, and a confirmation message is shown; the list is refreshed.  
   * If you do not have that book, you are notified that you do not appear to have it checked out.

**Checking out a book (borrowing):**

1. Select a title from the list.  
2. Click **Check Out**.  
   * If nothing is selected, a message tells you to select a book first.  
   * The system searches all shelves to find a `Book` with that title.  
   * If the book is already checked out, you are informed that it is unavailable.  
   * If it is available and you are below your maximum checkout count, the book is checked out to you and you see a success message; the list is refreshed.  
   * If you already have the maximum number of books (3), a message asks you to check in some books first.

## **4\. Paying fines**

From Patron Menu, click **Pay Fines** to open the **Patron Payment** window.

* The window shows your current fine amount as a dollar value label.  
* To **pay part or all of your fines**:  
  * Enter a payment amount in the formatted text field.  
  * Click **Pay**.  
  * The program converts the text to a number and calls the patron’s `pay` method, then updates the fine label and clears the input field.  
  * If the input cannot be parsed as a number, you are prompted to enter a valid number.

To return to the Patron Menu, click **Back**, which recreates PatronMenu with the same patron and library and closes the payment window.

## **5\. Logging out / switching to login**

From Patron Menu, click **Back** to return to the Patron Login window and close the menu.

---

## **Librarian User Guide**

## **1\. Logging in / creating librarian accounts**

Open the **Librarian Login** window (either at startup or via “Switch to Librarian Login” from the Patron Login).

* Enter username and password and click **Login**:  
  * The system loops through the library’s list of librarians and calls `authenticate` on each.  
  * On a successful match, the **Librarian Dashboard** opens and the login window closes.  
  * If no librarian matches, a dialog shows “Invalid login”.  
* To **create a new librarian**:  
  * Click **Create Librarian**.  
  * A dialog appears with fields for new username and password.  
  * After confirming, if either field is empty, you are told that fields cannot be empty.  
  * Otherwise, a new `Librarian` is created, added to the library, and a success message is shown.  
* To **switch to the Patron Login**, click **Switch to Patron Login**, which opens the patron login window and closes the librarian login.

## **2\. Librarian Dashboard – main navigation**

After login, the **Librarian Dashboard** provides buttons for all librarian operations:

* **View Catalog** – browse the full catalog.  
* **View Shelves** – browse shelves and their books.  
* **Add Book** – create a new book record and place it on a shelf.  
* **Add Shelf** – create a new shelf for organizing books.  
* **Check Out Books** – process a checkout from a cart for the current librarian user.  
* **Check In Books** – process check‑ins.  
* **Fine Management** – apply fines to patrons.  
* **Logout** – close the dashboard and return to librarian login.

Each button opens another window and closes the dashboard.

## **3\. Managing catalog and shelves**

**Viewing the catalog or a shelf:**

* **View Catalog** opens **BookListPage** with `shelf == null`, showing the full catalog from `library.getCatalog()`.  
* **View Shelves** opens **ViewShelvesPage**; from there, selecting a shelf can open **BookListPage** filtered to that shelf’s books.

In **BookListPage**:

* Each book row shows title, author, genre, and availability flag.  
* If the current user is a Librarian, each row includes an **Add to Cart** button that adds that book to the user’s cart.  
* The **Back** button returns to the Dashboard (or ViewShelvesPage when viewing a single shelf).

**Adding a new shelf (AddShelfPage):**

* Open via **Add Shelf** from the dashboard.  
* Fields: Shelf Name, Genre, Max Rows, Max Columns.  
* All fields are required; if any are empty you see an error message.  
* Rows and columns must be positive integers; invalid or non‑numeric entries trigger validation messages.  
* On success, a new `Shelf` is created and added to the library, you see “Shelf added successfully\!”, and the Dashboard reopens.

**Adding a new book (AddBookPage):**

* Open via **Add Book**.  
* Fields: Title, Author, Genre, Year, plus a dropdown of existing Shelves populated from `library.getShelves()`.  
* All text fields are required; missing values cause an error.  
* Year must be a valid integer; otherwise you get a “Year must be a valid number” message.  
* On success:  
  * A new `Book` is created and added to the library catalog.  
  * If a shelf is selected and not full, the book is also added to that shelf; if the shelf is full, you see an error and remain on the page.  
  * A confirmation appears and you are returned to the dashboard.

## **4\. Check‑out, check‑in, and fines (librarian side)**

**CheckoutPage (librarian cart checkout):**

* Opened via **Check Out Books**.  
* Displays a list of books currently in the librarian user’s cart, each with title, author, genre, and a **Remove** button.  
* Removing a book from the cart refreshes the page and shows a confirmation dialog.  
* Clicking **Confirm Checkout** iterates through the cart, calls `checkOut` on each book for the current user, clears the cart, shows “Checkout complete\!”, and returns to the Dashboard.  
* **Back** returns directly to the Dashboard without changing the cart.

**CheckInPage:**

* Opened via **Check In Books**; although the full implementation is not shown here, the pattern mirrors CheckoutPage with actions for returning books for the librarian user.

**FineManagementPage:**

* Opened via **Fine Management**.  
* Shows a list of all patrons; you select a patron to manage.  
* At the bottom, enter a numeric **Fine Amount** and click **Apply Fine**.  
  * If no patron is selected, you are prompted to select one.  
  * If the amount is non‑numeric or not positive, you see a validation message.  
  * On success, the specified amount is added as a fine to the selected patron and a confirmation dialog displays the amount and patron name.  
* **Back** returns to the Dashboard.

**Logging out:**

* From the Dashboard, click **Logout** to close it and re‑open the Librarian Login window.

## **Patron vs. Librarian GUI**

| Aspect | Patron GUI | Librarian GUI |
| :---- | :---- | :---- |
| Entry screen | PatronLogin – supports register and login for patrons. [PatronLogin-8.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/bcd42e5e-f542-4137-a518-5279b8fc4ce8/PatronLogin-8.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=Ameo2XgPrat3v5ZAPrR1v9EiMsE%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) | LibrarianLogin – supports login and creation of librarian accounts. [LibrarianLogin-26.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/88dab9e3-bbc8-4efe-b897-a7c9458075e8/LibrarianLogin-26.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=AnZJQG3lnLRxO2OV7YgGHl0ty3A%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) |
| Main menu | PatronMenu – list of book titles, search, check‑in/out, pay fines. [PatronMenu-10.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/42052c85-9d4c-44e9-8c13-cd59397b6405/PatronMenu-10.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=gjYlx5XozJOK7p4BuuJ%2BXNP1Fhk%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) | LibrarianDashboard – navigation to catalog, shelves, add book/shelf, checkout, checkin, fines. [LibrarianDashboard-25.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/8ef4c215-651d-41bb-b07b-0e061c5dde5c/LibrarianDashboard-25.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=i5DIW7xCXRa%2BDWDpcQIGwAWPw5A%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) |
| Search capability | Title text search filtering list of all books. [PatronMenu-10.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/42052c85-9d4c-44e9-8c13-cd59397b6405/PatronMenu-10.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=gjYlx5XozJOK7p4BuuJ%2BXNP1Fhk%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) | Catalog browsing via View Catalog and View Shelves; no free‑text search in dashboard itself. LibrarianDashboard-25.java+1 |
| Checkout behavior | Patron checks books out directly from global list of titles. [PatronMenu-10.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/42052c85-9d4c-44e9-8c13-cd59397b6405/PatronMenu-10.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=gjYlx5XozJOK7p4BuuJ%2BXNP1Fhk%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) | Librarian uses a cart and a dedicated CheckoutPage to confirm checkout. CheckoutPage-22.java+1 |
| Fine handling | PatronPayment lets patron pay down their own fines. [PatronPayment-12.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/be5a4807-892d-469e-927b-cde7a699e9b7/PatronPayment-12.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=8A5HC%2BDvTPnjnkQb0iqh2qdwOZE%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) | FineManagementPage lets librarian apply new fines to any patron. [FineManagementPage-23.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/59869a43-f4b1-48fc-8956-86eab359d421/FineManagementPage-23.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=uHX0WiHQDVbn4sTEaYVf62Bcd4I%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) |
| Account management from GUI | Patron can only create their own patron via registration. [PatronLogin-8.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/bcd42e5e-f542-4137-a518-5279b8fc4ce8/PatronLogin-8.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=Ameo2XgPrat3v5ZAPrR1v9EiMsE%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) | Librarian can create new librarian accounts via dialog. [LibrarianLogin-26.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/88dab9e3-bbc8-4efe-b897-a7c9458075e8/LibrarianLogin-26.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=AnZJQG3lnLRxO2OV7YgGHl0ty3A%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) |
| Role switching | Button to switch to Librarian Login. [PatronLogin-8.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/bcd42e5e-f542-4137-a518-5279b8fc4ce8/PatronLogin-8.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=Ameo2XgPrat3v5ZAPrR1v9EiMsE%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) | Button to switch to Patron Login. [LibrarianLogin-26.java](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/138442726/88dab9e3-bbc8-4efe-b897-a7c9458075e8/LibrarianLogin-26.java?AWSAccessKeyId=ASIA2F3EMEYESUYIIZGB&Signature=AnZJQG3lnLRxO2OV7YgGHl0ty3A%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEKv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIE%2B7I4eWW2%2FFcYEnfdgYBm%2F4Vx5uG7PlpcPq5mdn3LDjAiEA1Jf9M%2FNg0GFJaeobdwKnJM1ECKa7algLb7uAIGthZLoq8wQIdBABGgw2OTk3NTMzMDk3MDUiDCZ1jooTDrAIosw66yrQBCXXPSAwkpRe1w0iU8pZkfd3yLyM0hgOSLybaDF%2FZJvpG3p0mPg1Zxcar83rFieUbd0l5ZLi4qmDh4WHJgHg4NI7rlLM%2F%2FxYX3eXVqMyokwqVFRQqHPwGDxMYLd3bJRNNeWwIKrvvK51U9fSgHssGKPZfMrnKROSGnkRa76%2B2Vmgkyp1%2F%2BE2qA%2BCmwrYdLrCpNkjNstXq%2FjyhQ4MQNLYgRG3%2BEOk94foFaxhnwSjU6ItukvmA7WUwjztIl0BmQNC1y5%2BVK%2F2KF8%2BckK4khDoZ%2BtZZjVxuwzDMgYv4zyrzoL2OZuIXLM%2BSMmXO82tJS%2BaJwj5O2M6CNY0dHP1Smjf9FC8cTm8NHxr2t4qOjH2Z%2FXhI7NWLJLqL%2BjYtq64cgKoaDuqQ10D8GUawb%2F9NF3Mafr51L7XqbpaRsItj%2Bbvu1luL6WREMIxDT4e9ySKRl3qfsH24NMuHcQSbHeBMUKyv%2BgpO7uDCp1UpdxgdjMauQcd5IedqM2UfXb7CohVqKZWCApqvs2KZBEBj65FtJTDXRIXm0dTgnWT9Dfoz8KkIpMAHI2Qbzc2nmtjSMo1%2FR9YLE6QQd%2B0w1auvpLnntg4k3pIC3%2Bq5a%2FBXDgqXEglNAz0zq%2FM%2FHJHm%2FYsz6eXSqQsnLt%2FIf9lC4xrQPGHwGDl7VybDqwgHjz5CPCITO6TmfQpuiMG5%2BiPJsBM54q5Ul5ly1IuRdoHvhnt0fqO0aCuTYda1bW%2B7TXW%2BCu%2FWyCw1gmodjEf%2F7LLZ7HMecf0T%2F3J03btMnj7Ax%2BC5Wzx%2FmKwhhEw2c%2FjzwY6mAFBjOzR%2FQCe%2FZuy6orw%2Bl0Lwuof8joUE4eXaopcGQOA4tz8FXNIdAiabgFprnIYCuiGIxOLdhWRSWlOcMDm6P5IeUNrRElc1ljxTtJ9mnYfOGLkwPPPONObwRhQyLoX5ZnTJ4Pt8Xm1FoGHpnELfZUKTiRtekpd%2FbgrE6uB0LcQoFf3vzEu7%2FFdVu8CjuZDsGiVhdy6e353Wg%3D%3D&Expires=1777920782) |

