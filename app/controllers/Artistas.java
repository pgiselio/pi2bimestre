package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(GetUserLoggedIn.class)
public class Artistas extends Controller {

    public static void index() {
        render();
    }

}