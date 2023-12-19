/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxapplicationca;

import Calculation.*;
import Database.*;
import User.*;

/**
 *
 * @author 4istik
 */
public class Login extends AdminAndUserOptions {
    UserCredentialsManager user = new UserCredentialsManager();
    UserAction action = new UserAction();
    Calculation cal = new Calculation();
    DatabaseInteraction inter = new DatabaseInteraction();
}
