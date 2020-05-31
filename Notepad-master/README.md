# 期中测试
## 功能一：显示时间戳

### 1、修改notelist_item.xml，添加显示时间戳的TextView
先将原来的TextView布局改为垂直的线性布局，然后添加显示时间戳的TextView
 ``` Base
	<TextView
        android:id="@+id/time"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceSmall"
        android:paddingLeft="5dip"

        />
 ``` 

### 2、适配器中添加的更改时间戳，以及显示时间戳的TextView
 ``` java
		//添加更改时间数据
		String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE ,NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE} ;

		// The view IDs that will display the cursor columns, initialized to the TextView in
		// noteslist_item.xml
		//将数据填充到文本框中
		int[] viewIDs = { android.R.id.text1 ,R.id.time};

		// Creates the backing adapter for the ListView.
		SimpleCursorAdapter adapter
			= new SimpleCursorAdapter(
					  this,                             // The Context for the ListView
					  R.layout.noteslist_item,          // Points to the XML for a list item
					  cursor,                           // The cursor to get items from
					  dataColumns,
					  viewIDs
			  );
 ``` 

### 3、将时间戳投影
 ``` java
  private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            //将时间戳投影出来
            NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,
    };
 ``` 

### 4、在NotePadProvider.java中将时间戳格式化存入
 ``` java
		//将时间戳格式化存入
        Long now = Long.valueOf(System.currentTimeMillis());
        Date date = new Date(now);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String time = sf.format(date);
 ``` 
### 5、结果
![image](https://github.com/SeanVivi/Android/blob/master/images/time.png)


## 功能二：搜索功能（模糊搜索）
此功能的界面跳转模仿笔记编辑的界面跳转至笔记搜索页面，而搜索结果的显示则是模仿的笔记显示功能

### 1、布局搜索界面，在该界面设计搜索视图，还有搜索结果集合视图 
 ``` Base
	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
		android:orientation="vertical"
		android:layout_width="match_parent"
		android:layout_height="match_parent">
		
		<!--SearchView提供搜索输入-->
		<SearchView
			android:id="@+id/search_view"
			android:layout_width="match_parent"
			android:layout_height="wrap_content"
			android:iconifiedByDefault="false"
			android:queryHint="搜笔记..."
			android:layout_alignParentTop="true">
		</SearchView>
		
		<!-- ListView显示模糊搜索时的所有集合-->
		<ListView
			android:id="@android:id/list"
			android:layout_width="match_parent"
			android:layout_height="wrap_content">
		</ListView>
	</LinearLayout>
 ``` 

### 2、建立NoteSearch类，实现模糊搜索
搜索文件，通过模糊搜索，通过Cusor游标显示符合条件的笔记
 ``` java
	@Override
		public boolean onQueryTextChange(String title) {
			String condition = NotePad.Notes.COLUMN_NAME_TITLE + " Like ? ";
			String[] conditionArgs = { "%"+title+"%" };
			Cursor cursor = managedQuery(
					getIntent().getData(),            
					PROJECTION,                       
					condition,                        // 模糊搜索条件的前面几个字符
					conditionArgs,                    // 模糊搜索条件的中间或后面几个字符
					NotePad.Notes.DEFAULT_SORT_ORDER  
			);
			String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE ,  NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE };
			int[] viewIDs = { android.R.id.text1 , R.id.time };
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					this,
					R.layout.noteslist_item,
					cursor,
					dataColumns,
					viewIDs
			);
			setListAdapter(adapter);
			return true;
		}
	 ``` 
点击搜索出来的笔记的事件响应，模仿NoteList类，当点击笔记时，可跳转到当前笔记的编辑页面
 ``` java
	Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			// Constructs a new URI from the incoming URI and the row ID
			Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);
			// Gets the action from the incoming Intent
			String action = getIntent().getAction();
			// Handles requests for note data
			if (Intent.ACTION_PICK.equals(action) || Intent.ACTION_GET_CONTENT.equals(action)) {
				// Sets the result to return to the component that called this Activity. The
				// result contains the new URI
				setResult(RESULT_OK, new Intent().setData(uri));
			} else {
				// Sends out an Intent to start an Activity that can handle ACTION_EDIT. The
				// Intent's data is the note ID URI. The effect is to call NoteEdit.
				startActivity(new Intent(Intent.ACTION_EDIT, uri));
			}
		}
 ``` 

### 3、在list_options_menu.xml中添加搜索条目

 ``` 
	<item android:id="@+id/menu_search"
		android:title="@string/menu_search"
		android:icon="@drawable/ic_menu_search"
		android:showAsAction="always"
		 />
 ``` 

### 4、在NoteList类中的onOptionsItemSelected方法添加笔记搜索

 ``` java
	//增加笔记搜索
	case R.id.menu_search:
		  Intent intent =new Intent();
		  intent.setClass(NotesList.this,NoteSearch.class);
		  NotesList.this.startActivity(intent);
		  return true;
 ``` 

### 5、结果
![image](https://github.com/SeanVivi/Android/blob/master/images/Search.png)






