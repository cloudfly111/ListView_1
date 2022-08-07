# 學 ListView 元件 
>[2022-08-05 Android課程]

## project : ListView_1
主要目的是建立縣市名稱下拉選單(`Spinner 元件`)和顯示食物清單(`ListView元件`)，
使用者點選後，顯示對話框確認使用者所選的縣市名稱和食物品項後，
並跳至另一個頁面顯示食物品項名稱與對應的食物圖片。

![Screenshot_1659860861](https://user-images.githubusercontent.com/37395516/183282552-81b65a82-774e-4b54-a29d-cdbfe80b59e7.png)
![Screenshot_1659860868](https://user-images.githubusercontent.com/37395516/183282574-93f6b3ca-f19c-433a-9f2e-3e6fe4bc1f9e.png)

![Screenshot_1659860879](https://user-images.githubusercontent.com/37395516/183282577-62853ec6-8d6d-46aa-bd35-cbe4a9ec653d.png)
![Screenshot_1659860884](https://user-images.githubusercontent.com/37395516/183282587-ac4fa75a-dbcd-4577-97f5-47f9ff56ff4f.png)



#### 0. 開新的project , 取名為 "ListView_1"

#### 1. res/values/strings.xml 新增兩個string array  city和food:
```
<resources>
    <string name="app_name">ListView_1</string>
    <string-array name="city">
        <item>Taipei</item>
        <item>Hsinchu</item>
        <item>Taichung</item>
    </string-array>

    <string-array name="food">
        <item>Cola Light</item>
        <item>Cola Zero</item>
        <item>Cola</item>
        <item>French Fries</item>
        <item>Hamburger</item>
        <item>KFC</item>
    </string-array>
</resources>
```

#### 2. res/drawable 新增六張食物圖片 :
- coca_cola.png
- coca_cola_light.png
- coca_cola_zero.png
- french_fries.png
- hamburger.png
- kfc.png.png

#### 3. 處理activity_main.xml layout:
 3.1 新增 spinner (Containers):
 Id = spinner_id
 entries : @array/city

 3.2 新增ListView (Legacy)
 Id = listView_id
 entries : @array/food

#### 4. 處理MainActivity.java code : 
 + 設定 Spinner & ListView 元件
```
        spinnerCity = (Spinner)findViewById(R.id.spinner_id);
        listViewFood = (ListView)findViewById(R.id.listView_id);
//      [Spinner]
//      1. Adapter : provide Spinner two view
//      (1) before press spinner (2)after press spinner
//        1.1 call default layout
//        spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.city,
//                android.R.layout.simple_spinner_item);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCity.setAdapter(spinnerAdapter);
```
#### 5.
由 C:\Users\user\AppData\Local\Android\Sdk\platforms\android-32\data\res\layout 
複製以下兩個檔案到res/layout : 
simple_spinner_dropdown_item.xml
simple_spinner_item.xml

#### 6.
simple_spinner_dropdown_item.xml :
android:layout_height="?android:attr/dropdownListPreferredItemHeight"
改成 android:layout_height="match_parent"
新增
android:textColor="@color/design_default_color_primary"
android:textSize="20sp"
android:padding="10dp"

simple_spinner_item.xml :
新增
android:textSize="24sp"

#### 7. 處理MainActivity.java code : 
```
        spinnerCity = (Spinner)findViewById(R.id.spinner_id);
        listViewFood = (ListView)findViewById(R.id.listView_id);
//      [Spinner]
//      1. Adapter : provide Spinner two view
//      (1) before press spinner (2)after press spinner
//        1.1 call default layout
//        spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.city,
//                android.R.layout.simple_spinner_item);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCity.setAdapter(spinnerAdapter);
//        1.2 call self-defined layout :
//        layout/simple_spinner_item.xml
//        layout/simple_spinner_dropdown_item.xml
        spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.city,
                R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(spinnerAdapter);
```
#### 8. Adapter 是控制Spinner ListView 顯示資料的物件


#### 9.
由 C:\Users\user\AppData\Local\Android\Sdk\platforms\android-32\data\res\layout 
複製以下兩個檔案到res/layout : 
simple_expandable_list_item_1.xml
simple_list_item_1.xml

#### 10 . MainActivity.java設定ListView layout

#### 11. 新增DisplayActivity.java

#### 12. 監聽Spinner & ListView :
監聽Spinner : setOnItemSelectedListener
ListView : setOnItemClickListener

#### 13. 處理 DisplayActivity.java :
```
package com.example.listview_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {


    private TextView textViewName;
    private ImageView imageViewPic;
    private int[] foodPic = {
      R.drawable.coca_cola_light,
      R.drawable.coca_cola_zero,
      R.drawable.coca_cola,
      R.drawable.french_fries,
      R.drawable.hamburger,
      R.drawable.kfc
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ActionBar actBar = getSupportActionBar();
        actBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("city");
        setTitle(title);
        String foodName = intent.getStringExtra("food");

        textViewName= (TextView)findViewById(R.id.textView_food);
        textViewName.setText(foodName);

        int index = intent.getIntExtra("index", 0);
        imageViewPic = (ImageView) findViewById(R.id.imageView_id);
        imageViewPic.setImageResource(foodPic[index]);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
```

 + int[] foodPic 圖片array 可以用strings.xml取代 :
 
 ```
<string-array name="picture">
        <item>@drawable/coca_cola_light</item>
        <item>@drawable/coca_cola_zero</item>
        <item>@drawable/coca_cola</item>
        <item>@drawable/french_fries</item>
        <item>@drawable/hamburger</item>
        <item>@drawable/kfc</item>
    </string-array>
```   

並在 DisplayActivity 新增 TypedArray pictureList，pictureList = getResources().obtainTypedArray(R.array.picture);
取出array，比較特別的是使用TypedArray類別。





