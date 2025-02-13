# 0214

報價單產生器 (Quotation Generator)

總覽

報價單產生器是一個寫成的 Java 應用程式，用於生成報價單。這個程式使用 MVC 模式，包含用戶控制器 (Controller)，資料層 (DAO)，模型層 (Model)，服務層 (Service)，以及工具類。

安裝方式

確保您已安裝 Java 11

下載整個專案包

在 IDE (e.g., IntelliJ IDEA, Eclipse) 或系統終端中打開專案

執行 main 方法來啟動應用程式

使用方式

啟動程式

輸入相關資料（例如：訂單、顯示客戶資料等）

產生報價單

專案架構

1. Controller (控制器)

controller.customer (客戶管理)

controller.orders (訂單管理)

OrdersCreateUI.java (創建訂單介面)

OrdersListUI.java (訂單列表介面)

OrdersMainUI.java (訂單主介面)

2. DAO (資料存取層)

dao (提供數據庫操作的接口)

dao.impl (DAO 具體實作)

3. Model (模型層)

Customer.java (客戶模型)

OrderItems.java (訂單項目模型)

Orders.java (訂單模型)

4. Service (業務邏輯層)

service (提供業務邏輯功能)

service.impl (業務邏輯實作)

5. Util (工具類)

DbConnection.java (數據庫連接管理)

TimeUtil.java (時間工具類)

設定方式

需要設定 MySQL 數據庫以存儲報價單資料

需要設定數據庫連接資訊 (請在 DbConnection.java 設置)

測試方式

目前尚未提供測試檔案，建議手動驗證功能是否正常。

需求資料

需要特定的輸入文件來處理訂單，請參考範例資料。

範例數據可透過 MySQL 匯入。

已知問題

目前無法修改管理商品。

未來計畫

支援商品管理功能，允許新增、刪除和修改商品資訊。

提供可視化介面來簡化操作。

增加多用戶支援。

引入測試機制，確保程式穩定性。

授權

本專案目前為私人專案，未提供公開授權。如需使用，請聯絡作者。
