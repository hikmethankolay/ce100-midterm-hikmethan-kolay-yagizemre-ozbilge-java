/**

@file RentalManagementLibTest.java
@brief This file contains the test cases for the RentalManagementLib class.
@details This file includes test methods to validate the functionality of the RentalManagementLib class. It uses JUnit for unit testing.
*/
package com.project.RentalManagement;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.project.RentalManagementLib.RentalManagementLib;

/**

@class RentalManagementLibTest
@brief This class represents the test class for the RentalManagementLib class.
@details The RentalManagementLibTest class provides test methods to verify the behavior of the RentalManagementLib class. It includes test methods for addition, subtraction, multiplication, and division operations.
@author hikmethankolay
*/
public class RentalManagementLibTest {

  /**
   * @brief This method is executed once before all test methods.
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  /**
   * @brief This method is executed once after all test methods.
   * @throws Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  /**
   * @brief This method is executed before each test method.
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @brief This method is executed after each test method.
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
       * Indicates a test failure.
       */
  int fail = -1;

  /**
   * Indicates a successful test.
   */
  int success = 0;

  /**
   * Tests the file_read function.
   */
  @Test
  public void testFileRead() {
    String testString = "1-)TEXT STRING1\n2-)TEXT STRING2\n3-)TEXT STRING3\n4-)TEXT STRING4\n5-)TEXT STRING5\n";
    assertEquals(testString, RentalManagementLib.file_read("test1.bin", 'N'));
  }

  /**
   * Tests the file_append function.
   */
  @Test
  public void testfile_append() {
    String testString = "1-)TEXT STRING1\n2-)TEXT STRING2\n3-)TEXT STRING3\n4-)TEXT STRING4\n5-)TEXT STRING5\n6-)TEXT STRING6\n";
    String appendString = "TEXT STRING6";
    RentalManagementLib.file_append("test2.bin", appendString);
    assertEquals(testString, RentalManagementLib.file_read("test2.bin", 'N'));
  }

  /**
   * Tests the file_edit function.
   */
  @Test
  public void testFileEdit() {
    String testString = "1-)TEXT STRING1\n2-)TEXT STRING2\n3-)TEXT STRING EDIT\n4-)TEXT STRING4\n5-)TEXT STRING5\n";
    String editString = "TEXT STRING EDIT";
    RentalManagementLib.file_edit("test3.bin", 3, editString);
    assertEquals(testString, RentalManagementLib.file_read("test3.bin", 'N'));
  }

  /**
   * Tests the file_line_delete function.
   */
  @Test
  public void testFileDelete() {
    String testString = "1-)TEXT STRING2\n2-)TEXT STRING3\n3-)TEXT STRING4\n4-)TEXT STRING5\n";
    RentalManagementLib.file_line_delete("test4.bin", 1);
    assertEquals(testString, RentalManagementLib.file_read("test4.bin", 'N'));
  }

  /**
   * Tests the file_write function.
   */
  @Test
  public void testFileWrite() {
    String testString = "1-)TEXT STRING WRITE\n";
    String writeString = "TEXT STRING WRITE";
    RentalManagementLib.file_write("test5.bin", writeString);
    assertEquals(testString, RentalManagementLib.file_read("test5.bin", 'N'));
  }

  /**
   * Tests the file_read function in a fail case.
   */
  @Test
  public void testFileReadFail() {
    assertEquals("-1",RentalManagementLib.file_read("test1f.bin", 'N'));
  }

  /**
   * Tests the file_append function in a fail case.
   */
  @Test
  public void testFileAppendFail() {
    String appendString = "TEXT STRING5";
    assertEquals(fail, RentalManagementLib.file_append("test2f.bin", appendString));
  }

  /**
   * Tests the file_edit function in a fail case.
   */
  @Test
  public void testFileEditFail() {
    String editString = "TEXT STRING EDIT";
    assertEquals(fail, RentalManagementLib.file_edit("test3f.bin", 3, editString));
  }

  /**
   * Tests the file_edit function in a fail case (line number out of range).
   */
  @Test
  public void testFileEditFail_2() {
    String editString = "TEXT STRING EDIT";
    assertEquals(fail, RentalManagementLib.file_edit("test3.bin", 100, editString));
  }

  /**
   * Tests the file_line_delete function in a fail case.
   */
  @Test
  public void testFileDeleteFail() {
    assertEquals(fail, RentalManagementLib.file_line_delete("test4f.bin", 2));
  }

  /**
   * Tests the file_line_delete function in a fail case (line number out of range).
   */
  @Test
  public void testFileDeleteFail_2() {
    assertEquals(fail, RentalManagementLib.file_line_delete("test4.bin", 100));
  }

  /**
       * Tests the user_login function in a fail case.
       */
  @Test
  public void testUserLoginFail() {
    assertEquals(fail, RentalManagementLib.user_login("username", "passwordaa", "usertest.bin"));
  }

  /**
   * Tests the user_change_password function in a fail case.
   */
  @Test
  public void testUserChangePasswordFail() {
    assertEquals(fail, RentalManagementLib.user_change_password("recoverykey", "newpassword", "usertest.bin"));
  }

  /**
   * Tests the user_register function.
   */
  @Test
  public void testUserRegister() {
    String testString = "username/password/recoverykey";
    RentalManagementLib.user_register("username", "password", "recoverykey", "usertest.bin");
    assertEquals(testString, RentalManagementLib.file_read("usertest.bin", 'N'));
  }

  /**
   * Tests the user_login function.
   */
  @Test
  public void testUserLogin() {
    assertEquals(success, RentalManagementLib.user_login("username", "password", "usertest.bin"));
  }

  /**
   * Tests the user_login function in a fail case.
   */
  @Test
  public void testUserLoginFail_2() {
    assertEquals(fail, RentalManagementLib.user_login("usernameaa", "passwordaa", "usertest.bin"));
  }

  /**
   * Tests the user_change_password function.
   */
  @Test
  public void testUserChangePassword() {
    assertEquals(success, RentalManagementLib.user_change_password("recoverykey", "newpassword", "usertest.bin"));
  }

  /**
   * Tests the user_change_password function in a fail case.
   */
  @Test
  public void testUserChangePasswordFail_2() {
    assertEquals(fail, RentalManagementLib.user_change_password("recoverykeyaa", "newpassword", "usertest.bin"));
  }

}
