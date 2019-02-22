package com.company.project.util;
import java.awt.List;
import java.io.File;  
import java.awt.List;
import java.util.ArrayList;
import java.util.Dictionary;
import java.io.InputStreamReader;  
import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileInputStream;  
import java.io.FileWriter; 
import java.io.IOException; 
import java.util.ArrayList;


public class TableTools
{

		public static int[] StringArrToIntArr(String[] target)
		{
			int[] m = new int[target.length];
			for(int i = 0; i < target.length; i++)
			{
				m[i] = Integer.parseInt(target[i]);
			}
			return m;
		}
		
		public static boolean[] StringArrToBoolArr(String[] target)
		{
			boolean[] m = new boolean[target.length];
			for(int i = 0; i < target.length; i++)
			{
				m[i] = Boolean.parseBoolean(target[i]);
			}
			return m;
		}
		
		public static float[] StringArrToFloatArr(String[] target)
		{
			float[] m = new float[target.length];
			for(int i = 0; i < target.length; i++)
			{
				m[i] = Float.parseFloat(target[i]);
			}
			return m;
		}

		static ArrayList<card_config> all_card_config = new ArrayList<card_config>();

		public class card_config {
			public int id;
			public String desc;
			public String name;
			public int rarity;
			public int base_imagination;
			public int base_creativity;
			public int base_action;
			public int base_aesthetic;
			public String mini_icon;
			public String detail;
}
		public void Add_card_config_ToList(String s){
			String[] m = s.split("	");
			card_config w = new card_config();
			w.id = Integer.parseInt(m[0]);
			w.desc = m[1];
			w.name = m[2];
			w.rarity = Integer.parseInt(m[3]);
			w.base_imagination = Integer.parseInt(m[4]);
			w.base_creativity = Integer.parseInt(m[5]);
			w.base_action = Integer.parseInt(m[6]);
			w.base_aesthetic = Integer.parseInt(m[7]);
			w.mini_icon = m[8];
			w.detail = m[9];
			all_card_config.add(w);
}
		public static card_config Get_card_config_byid(int id){
			int l = all_card_config.size();
			for(int i = 0; i < l; i++)
			{
				card_config _config = all_card_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static card_config Get_card_config_byindex(int index)
	{
		int l = all_card_config.size();
		for(int i = 0; i < l; i++)
		{
			card_config _config = all_card_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<card_config> Get_card_config()
	{
		return all_card_config;
	}
			static ArrayList<card_level_config> all_card_level_config = new ArrayList<card_level_config>();

		public class card_level_config {
			public int id;
			public int n_need_exp;
			public int h_need_exp;
			public int r_need_exp;
			public int sr_need_exp;
			public int ssr_need_exp;
			public int n_attr;
			public int h_attr;
			public int s_attr;
			public int sr_attr;
			public int ssr_attr;
}
		public void Add_card_level_config_ToList(String s){
			String[] m = s.split("	");
			card_level_config w = new card_level_config();
			w.id = Integer.parseInt(m[0]);
			w.n_need_exp = Integer.parseInt(m[1]);
			w.h_need_exp = Integer.parseInt(m[2]);
			w.r_need_exp = Integer.parseInt(m[3]);
			w.sr_need_exp = Integer.parseInt(m[4]);
			w.ssr_need_exp = Integer.parseInt(m[5]);
			w.n_attr = Integer.parseInt(m[6]);
			w.h_attr = Integer.parseInt(m[7]);
			w.s_attr = Integer.parseInt(m[8]);
			w.sr_attr = Integer.parseInt(m[9]);
			w.ssr_attr = Integer.parseInt(m[10]);
			all_card_level_config.add(w);
}
		public static card_level_config Get_card_level_config_byid(int id){
			int l = all_card_level_config.size();
			for(int i = 0; i < l; i++)
			{
				card_level_config _config = all_card_level_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static card_level_config Get_card_level_config_byindex(int index)
	{
		int l = all_card_level_config.size();
		for(int i = 0; i < l; i++)
		{
			card_level_config _config = all_card_level_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<card_level_config> Get_card_level_config()
	{
		return all_card_level_config;
	}
			static ArrayList<card_star_config> all_card_star_config = new ArrayList<card_star_config>();

		public class card_star_config {
			public int id;
			public int[] n_random_interval_array;
			public int[] h_random_interval_array;
			public int[] r_random_interval_array;
			public int[] sr_random_interval_array;
			public int[] ssr_random_interval_array;
}
		public void Add_card_star_config_ToList(String s){
			String[] m = s.split("	");
			card_star_config w = new card_star_config();
			w.id = Integer.parseInt(m[0]);
			int[] n_random_interval_arrayArr = StringArrToIntArr(m[1].split(","));
			int[] h_random_interval_arrayArr = StringArrToIntArr(m[2].split(","));
			int[] r_random_interval_arrayArr = StringArrToIntArr(m[3].split(","));
			int[] sr_random_interval_arrayArr = StringArrToIntArr(m[4].split(","));
			int[] ssr_random_interval_arrayArr = StringArrToIntArr(m[5].split(","));
			all_card_star_config.add(w);
}
		public static card_star_config Get_card_star_config_byid(int id){
			int l = all_card_star_config.size();
			for(int i = 0; i < l; i++)
			{
				card_star_config _config = all_card_star_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static card_star_config Get_card_star_config_byindex(int index)
	{
		int l = all_card_star_config.size();
		for(int i = 0; i < l; i++)
		{
			card_star_config _config = all_card_star_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<card_star_config> Get_card_star_config()
	{
		return all_card_star_config;
	}
			static ArrayList<card_up_config> all_card_up_config = new ArrayList<card_up_config>();

		public class card_up_config {
			public int id;
			public String desc;
			public int[] n_need_item_array;
			public int[] n_need_item_num_array;
			public int[] h_need_item_array;
			public int[] h_need_item_num_array;
			public int[] r_need_item_array;
			public int[] r_need_item_num_array;
			public int[] sr_need_item_array;
			public int[] sr_need_item_num_array;
			public int[] ssr_need_item_array;
			public int[] ssr_need_item_num_array;
			public int n_need_coin;
			public int h_need_coin;
			public int s_need_coin;
			public int sr_need_coin;
			public int ssr_need_coin;
			public int n_attr;
			public int h_attr;
			public int s_attr;
			public int sr_attr;
			public int ssr_attr;
}
		public void Add_card_up_config_ToList(String s){
			String[] m = s.split("	");
			card_up_config w = new card_up_config();
			w.id = Integer.parseInt(m[0]);
			w.desc = m[1];
			int[] n_need_item_arrayArr = StringArrToIntArr(m[2].split(","));
			int[] n_need_item_num_arrayArr = StringArrToIntArr(m[3].split(","));
			int[] h_need_item_arrayArr = StringArrToIntArr(m[4].split(","));
			int[] h_need_item_num_arrayArr = StringArrToIntArr(m[5].split(","));
			int[] r_need_item_arrayArr = StringArrToIntArr(m[6].split(","));
			int[] r_need_item_num_arrayArr = StringArrToIntArr(m[7].split(","));
			int[] sr_need_item_arrayArr = StringArrToIntArr(m[8].split(","));
			int[] sr_need_item_num_arrayArr = StringArrToIntArr(m[9].split(","));
			int[] ssr_need_item_arrayArr = StringArrToIntArr(m[10].split(","));
			int[] ssr_need_item_num_arrayArr = StringArrToIntArr(m[11].split(","));
			w.n_need_coin = Integer.parseInt(m[12]);
			w.h_need_coin = Integer.parseInt(m[13]);
			w.s_need_coin = Integer.parseInt(m[14]);
			w.sr_need_coin = Integer.parseInt(m[15]);
			w.ssr_need_coin = Integer.parseInt(m[16]);
			w.n_attr = Integer.parseInt(m[17]);
			w.h_attr = Integer.parseInt(m[18]);
			w.s_attr = Integer.parseInt(m[19]);
			w.sr_attr = Integer.parseInt(m[20]);
			w.ssr_attr = Integer.parseInt(m[21]);
			all_card_up_config.add(w);
}
		public static card_up_config Get_card_up_config_byid(int id){
			int l = all_card_up_config.size();
			for(int i = 0; i < l; i++)
			{
				card_up_config _config = all_card_up_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static card_up_config Get_card_up_config_byindex(int index)
	{
		int l = all_card_up_config.size();
		for(int i = 0; i < l; i++)
		{
			card_up_config _config = all_card_up_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<card_up_config> Get_card_up_config()
	{
		return all_card_up_config;
	}
			static ArrayList<chapter_config> all_chapter_config = new ArrayList<chapter_config>();

		public class chapter_config {
			public int id;
			public int is_story;
			public int story_id;
			public int instance_id;
			public int player_level;
			public int is_last;
}
		public void Add_chapter_config_ToList(String s){
			String[] m = s.split("	");
			chapter_config w = new chapter_config();
			w.id = Integer.parseInt(m[0]);
			w.is_story = Integer.parseInt(m[1]);
			w.story_id = Integer.parseInt(m[2]);
			w.instance_id = Integer.parseInt(m[3]);
			w.player_level = Integer.parseInt(m[4]);
			w.is_last = Integer.parseInt(m[5]);
			all_chapter_config.add(w);
}
		public static chapter_config Get_chapter_config_byid(int id){
			int l = all_chapter_config.size();
			for(int i = 0; i < l; i++)
			{
				chapter_config _config = all_chapter_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static chapter_config Get_chapter_config_byindex(int index)
	{
		int l = all_chapter_config.size();
		for(int i = 0; i < l; i++)
		{
			chapter_config _config = all_chapter_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<chapter_config> Get_chapter_config()
	{
		return all_chapter_config;
	}
			static ArrayList<draw_instance_config> all_draw_instance_config = new ArrayList<draw_instance_config>();

		public class draw_instance_config {
			public int id;
			public String title;
			public String detail;
			public int[] reward_bag_array;
			public int[] reward_show_item_array;
			public int day_enter_num;
			public int consum_type;
			public int consum_num;
			public String tex_path;
			public String[] viewing_icon_path_array;
			public int[] viewing_thinking_attr_array;
			public int[] viewing_creation_attr_array;
			public int[] viewing_action_attr_array;
			public int[] viewing_aesthetic_attr_array;
			public int[] style_thinking_attr_array;
			public int[] style_creation_attr_array;
			public int[] style_action_attr_array;
			public int[] style_aesthetic_attr_array;
			public int[] one_star_need_attr_array;
			public int[] two_star_need_attr_array;
			public int[] three_star_need_attr_array;
}
		public void Add_draw_instance_config_ToList(String s){
			String[] m = s.split("	");
			draw_instance_config w = new draw_instance_config();
			w.id = Integer.parseInt(m[0]);
			w.title = m[1];
			w.detail = m[2];
			int[] reward_bag_arrayArr = StringArrToIntArr(m[3].split(","));
			int[] reward_show_item_arrayArr = StringArrToIntArr(m[4].split(","));
			w.day_enter_num = Integer.parseInt(m[5]);
			w.consum_type = Integer.parseInt(m[6]);
			w.consum_num = Integer.parseInt(m[7]);
			w.tex_path = m[8];
			String[] viewing_icon_path_arrayArr = m[9].split(",");
			int[] viewing_thinking_attr_arrayArr = StringArrToIntArr(m[10].split(","));
			int[] viewing_creation_attr_arrayArr = StringArrToIntArr(m[11].split(","));
			int[] viewing_action_attr_arrayArr = StringArrToIntArr(m[12].split(","));
			int[] viewing_aesthetic_attr_arrayArr = StringArrToIntArr(m[13].split(","));
			int[] style_thinking_attr_arrayArr = StringArrToIntArr(m[14].split(","));
			int[] style_creation_attr_arrayArr = StringArrToIntArr(m[15].split(","));
			int[] style_action_attr_arrayArr = StringArrToIntArr(m[16].split(","));
			int[] style_aesthetic_attr_arrayArr = StringArrToIntArr(m[17].split(","));
			int[] one_star_need_attr_arrayArr = StringArrToIntArr(m[18].split(","));
			int[] two_star_need_attr_arrayArr = StringArrToIntArr(m[19].split(","));
			int[] three_star_need_attr_arrayArr = StringArrToIntArr(m[20].split(","));
			all_draw_instance_config.add(w);
}
		public static draw_instance_config Get_draw_instance_config_byid(int id){
			int l = all_draw_instance_config.size();
			for(int i = 0; i < l; i++)
			{
				draw_instance_config _config = all_draw_instance_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static draw_instance_config Get_draw_instance_config_byindex(int index)
	{
		int l = all_draw_instance_config.size();
		for(int i = 0; i < l; i++)
		{
			draw_instance_config _config = all_draw_instance_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<draw_instance_config> Get_draw_instance_config()
	{
		return all_draw_instance_config;
	}
			static ArrayList<drop_item_config> all_drop_item_config = new ArrayList<drop_item_config>();

		public class drop_item_config {
			public int id;
			public int[] drop_consum_type_array;
			public int[] drop_consum_num_min_array;
			public int[] drop_consum_num_max_array;
			public int[] drop_item_type_array;
			public int[] drop_item_num_min_array;
			public int[] drop_item_num_max_array;
}
		public void Add_drop_item_config_ToList(String s){
			String[] m = s.split("	");
			drop_item_config w = new drop_item_config();
			w.id = Integer.parseInt(m[0]);
			int[] drop_consum_type_arrayArr = StringArrToIntArr(m[1].split(","));
			int[] drop_consum_num_min_arrayArr = StringArrToIntArr(m[2].split(","));
			int[] drop_consum_num_max_arrayArr = StringArrToIntArr(m[3].split(","));
			int[] drop_item_type_arrayArr = StringArrToIntArr(m[4].split(","));
			int[] drop_item_num_min_arrayArr = StringArrToIntArr(m[5].split(","));
			int[] drop_item_num_max_arrayArr = StringArrToIntArr(m[6].split(","));
			all_drop_item_config.add(w);
}
		public static drop_item_config Get_drop_item_config_byid(int id){
			int l = all_drop_item_config.size();
			for(int i = 0; i < l; i++)
			{
				drop_item_config _config = all_drop_item_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static drop_item_config Get_drop_item_config_byindex(int index)
	{
		int l = all_drop_item_config.size();
		for(int i = 0; i < l; i++)
		{
			drop_item_config _config = all_drop_item_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<drop_item_config> Get_drop_item_config()
	{
		return all_drop_item_config;
	}
			static ArrayList<error_config> all_error_config = new ArrayList<error_config>();

		public class error_config {
			public int id;
			public String info;
}
		public void Add_error_config_ToList(String s){
			String[] m = s.split("	");
			error_config w = new error_config();
			w.id = Integer.parseInt(m[0]);
			w.info = m[1];
			all_error_config.add(w);
}
		public static error_config Get_error_config_byid(int id){
			int l = all_error_config.size();
			for(int i = 0; i < l; i++)
			{
				error_config _config = all_error_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static error_config Get_error_config_byindex(int index)
	{
		int l = all_error_config.size();
		for(int i = 0; i < l; i++)
		{
			error_config _config = all_error_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<error_config> Get_error_config()
	{
		return all_error_config;
	}
			static ArrayList<iap_store_config> all_iap_store_config = new ArrayList<iap_store_config>();

		public class iap_store_config {
			public int id;
			public String desc;
			public int operation_sys;
			public String product_id;
			public String name;
			public String sub_name;
			public String detailed ;
			public String icon;
			public float price;
			public int reward_vip_level;
			public int reward_coin_num;
			public int reward_gem_num;
			public int reward_energy_num;
}
		public void Add_iap_store_config_ToList(String s){
			String[] m = s.split("	");
			iap_store_config w = new iap_store_config();
			w.id = Integer.parseInt(m[0]);
			w.desc = m[1];
			w.operation_sys = Integer.parseInt(m[2]);
			w.product_id = m[3];
			w.name = m[4];
			w.sub_name = m[5];
			w.detailed  = m[6];
			w.icon = m[7];
			w.price = Float.parseFloat(m[8]);
			w.reward_vip_level = Integer.parseInt(m[9]);
			w.reward_coin_num = Integer.parseInt(m[10]);
			w.reward_gem_num = Integer.parseInt(m[11]);
			w.reward_energy_num = Integer.parseInt(m[12]);
			all_iap_store_config.add(w);
}
		public static iap_store_config Get_iap_store_config_byid(int id){
			int l = all_iap_store_config.size();
			for(int i = 0; i < l; i++)
			{
				iap_store_config _config = all_iap_store_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static iap_store_config Get_iap_store_config_byindex(int index)
	{
		int l = all_iap_store_config.size();
		for(int i = 0; i < l; i++)
		{
			iap_store_config _config = all_iap_store_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<iap_store_config> Get_iap_store_config()
	{
		return all_iap_store_config;
	}
			static ArrayList<item_config> all_item_config = new ArrayList<item_config>();

		public class item_config {
			public int id;
			public String desc;
			public String name;
			public String detail;
			public String icon_path;
			public int exp;
}
		public void Add_item_config_ToList(String s){
			String[] m = s.split("	");
			item_config w = new item_config();
			w.id = Integer.parseInt(m[0]);
			w.desc = m[1];
			w.name = m[2];
			w.detail = m[3];
			w.icon_path = m[4];
			w.exp = Integer.parseInt(m[5]);
			all_item_config.add(w);
}
		public static item_config Get_item_config_byid(int id){
			int l = all_item_config.size();
			for(int i = 0; i < l; i++)
			{
				item_config _config = all_item_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static item_config Get_item_config_byindex(int index)
	{
		int l = all_item_config.size();
		for(int i = 0; i < l; i++)
		{
			item_config _config = all_item_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<item_config> Get_item_config()
	{
		return all_item_config;
	}
			static ArrayList<shop_config> all_shop_config = new ArrayList<shop_config>();

		public class shop_config {
			public int id;
			public String desc;
			public int consum_type;
			public String name;
			public String sub_name;
			public String detailed ;
			public String icon_prefab_path;
}
		public void Add_shop_config_ToList(String s){
			String[] m = s.split("	");
			shop_config w = new shop_config();
			w.id = Integer.parseInt(m[0]);
			w.desc = m[1];
			w.consum_type = Integer.parseInt(m[2]);
			w.name = m[3];
			w.sub_name = m[4];
			w.detailed  = m[5];
			w.icon_prefab_path = m[6];
			all_shop_config.add(w);
}
		public static shop_config Get_shop_config_byid(int id){
			int l = all_shop_config.size();
			for(int i = 0; i < l; i++)
			{
				shop_config _config = all_shop_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static shop_config Get_shop_config_byindex(int index)
	{
		int l = all_shop_config.size();
		for(int i = 0; i < l; i++)
		{
			shop_config _config = all_shop_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<shop_config> Get_shop_config()
	{
		return all_shop_config;
	}
			static ArrayList<story_1_1_config> all_story_1_1_config = new ArrayList<story_1_1_config>();

		public class story_1_1_config {
			public int id;
			public float time;
			public String bg_music_path;
			public String sound_path;
			public String bg_image_path;
			public String foreground_path;
			public int story_bg_type;
			public String story_content;
			public String story_title;
			public String story_icon_path;
}
		public void Add_story_1_1_config_ToList(String s){
			String[] m = s.split("	");
			story_1_1_config w = new story_1_1_config();
			w.id = Integer.parseInt(m[0]);
			w.time = Float.parseFloat(m[1]);
			w.bg_music_path = m[2];
			w.sound_path = m[3];
			w.bg_image_path = m[4];
			w.foreground_path = m[5];
			w.story_bg_type = Integer.parseInt(m[6]);
			w.story_content = m[7];
			w.story_title = m[8];
			w.story_icon_path = m[9];
			all_story_1_1_config.add(w);
}
		public static story_1_1_config Get_story_1_1_config_byid(int id){
			int l = all_story_1_1_config.size();
			for(int i = 0; i < l; i++)
			{
				story_1_1_config _config = all_story_1_1_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static story_1_1_config Get_story_1_1_config_byindex(int index)
	{
		int l = all_story_1_1_config.size();
		for(int i = 0; i < l; i++)
		{
			story_1_1_config _config = all_story_1_1_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<story_1_1_config> Get_story_1_1_config()
	{
		return all_story_1_1_config;
	}
			static ArrayList<story_config> all_story_config = new ArrayList<story_config>();

		public class story_config {
			public int id;
			public String story_desc_config_table;
			public int drop_card_id;
}
		public void Add_story_config_ToList(String s){
			String[] m = s.split("	");
			story_config w = new story_config();
			w.id = Integer.parseInt(m[0]);
			w.story_desc_config_table = m[1];
			w.drop_card_id = Integer.parseInt(m[2]);
			all_story_config.add(w);
}
		public static story_config Get_story_config_byid(int id){
			int l = all_story_config.size();
			for(int i = 0; i < l; i++)
			{
				story_config _config = all_story_config.get(i);
				if(_config.id == id)
				{
					return _config;
				}
			}
			return null;
		}
		public static story_config Get_story_config_byindex(int index)
	{
		int l = all_story_config.size();
		for(int i = 0; i < l; i++)
		{
			story_config _config = all_story_config.get(i);
			
			if(i == index)
			{
				return _config;
			}
		}
		
		return null;
		}
	public static ArrayList<story_config> Get_story_config()
	{
		return all_story_config;
	}
	
			private String TableToolsPath = "";
			
			public void InitTableTools(String path)
			{
				TableToolsPath = path;
			Init_card_config();
			Init_card_level_config();
			Init_card_star_config();
			Init_card_up_config();
			Init_chapter_config();
			Init_draw_instance_config();
			Init_drop_item_config();
			Init_error_config();
			Init_iap_store_config();
			Init_item_config();
			Init_shop_config();
			Init_story_1_1_config();
			Init_story_config();
}
	
			public ArrayList<String> ReadTable(String path)
			{
				try { 
		            String pathname = TableToolsPath + "/" + path;  
		            File filename = new File(pathname); 
		            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); 
		            BufferedReader br = new BufferedReader(reader); 
		            String line = "";  
		            line = br.readLine(); 
		            ArrayList<String> allInfo = new ArrayList<String>();
		            while (line != null) {  
		            	allInfo.add(line);
		                line = br.readLine(); 
		            }
		            reader.close();
		            br.close();
		            return allInfo;

		        } catch (Exception e) 
		        {
		            e.printStackTrace();  
		        } 
				return null;
			};
				private void Init_card_config(){
			String table_name = "card_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_card_config_ToList(_config);
		}		
}				private void Init_card_level_config(){
			String table_name = "card_level_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_card_level_config_ToList(_config);
		}		
}				private void Init_card_star_config(){
			String table_name = "card_star_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_card_star_config_ToList(_config);
		}		
}				private void Init_card_up_config(){
			String table_name = "card_up_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_card_up_config_ToList(_config);
		}		
}				private void Init_chapter_config(){
			String table_name = "chapter_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_chapter_config_ToList(_config);
		}		
}				private void Init_draw_instance_config(){
			String table_name = "draw_instance_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_draw_instance_config_ToList(_config);
		}		
}				private void Init_drop_item_config(){
			String table_name = "drop_item_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_drop_item_config_ToList(_config);
		}		
}				private void Init_error_config(){
			String table_name = "error_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_error_config_ToList(_config);
		}		
}				private void Init_iap_store_config(){
			String table_name = "iap_store_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_iap_store_config_ToList(_config);
		}		
}				private void Init_item_config(){
			String table_name = "item_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_item_config_ToList(_config);
		}		
}				private void Init_shop_config(){
			String table_name = "shop_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_shop_config_ToList(_config);
		}		
}				private void Init_story_1_1_config(){
			String table_name = "story_1_1_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_story_1_1_config_ToList(_config);
		}		
}				private void Init_story_config(){
			String table_name = "story_config.txt";
				ArrayList<String> allInfo = ReadTable(table_name);
					int l = allInfo.size();
					for(int i = 4; i < l; i++)
					{
						String _config = allInfo.get(i);
			Add_story_config_ToList(_config);
		}		
}		
}