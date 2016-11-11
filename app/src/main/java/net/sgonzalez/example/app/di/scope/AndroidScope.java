package net.sgonzalez.example.app.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

@Scope @Retention(RetentionPolicy.RUNTIME) public @interface AndroidScope {
  String value() default "";
}
