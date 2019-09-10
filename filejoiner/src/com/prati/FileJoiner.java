package com.prati;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileJoiner {

	public static void main(String[] args) {
		try {
			byte b[] = new byte[900];
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.println("enter first part of file path: ");

			String part_path = br.readLine();

			String file_name = part_path.substring(0, part_path.lastIndexOf("."));
			String part_number = part_path.substring(part_path.lastIndexOf(".") + 1);
			String file_extension = file_name.substring(file_name.lastIndexOf("."));

			System.out.println("enter Join or new File Path name: ");
			String new_path = br.readLine();

			File combine_file_path = new File(new_path);

			String path_parent = combine_file_path.getParent();

			int flag = 0;
			if (new_path.endsWith(file_extension)) {
				flag = 1;
			}
			
			File check_part_file_path = new File(part_path);
			File check_new_file_path = new File(path_parent);
			/*
			 * System.out.println(check_part_file_path); System.out.println(flag);
			 * System.out.println(check_new_file_path);
			 * System.out.println(check_part_file_path.getAbsoluteFile().exists());
			 * System.out.println(check_new_file_path.exists());
			 */            //check_part_file_path.exists() &&
			if (check_part_file_path.getAbsoluteFile().exists() && flag == 1 && check_new_file_path.exists()) {
				FileOutputStream fos = new FileOutputStream(new_path);
				int x = 1;
				int read_bytes;
				String parts_name_path = "";

				while (true) {
					parts_name_path = "";
					if (part_number.startsWith("00")) {
						parts_name_path = file_name + ".00" + x;
					} else {
						parts_name_path = file_name + ".0" + x;
					}

					File f = new File(parts_name_path);
					if (f.exists()) {
						FileInputStream fis = new FileInputStream(parts_name_path);
						while (fis.available() != 0) {
							read_bytes = fis.read(b, 0, 900);
							fos.write(b, 0, 900);
							
						}

						System.out.println("#### Part " + x + " joined ####");
						fis.close();
						x++;
					} else {
						System.out.println("File joined successfully");
						break;
					}
				}
			}

			else if (!check_new_file_path.exists()) {
				System.out.println("##### You write wrong path of new file ####");
			} else if (flag == 0) {
				System.out.println("#### new file extension does not match with part file extension ####");
			} else {
				System.out.println("#### file path of first file does not exists #####");
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

}
