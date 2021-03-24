import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LessonComponent } from './components/lesson/lesson.component';
import { CourseComponent } from './components/course/course.component';
import { CreateLessonComponent } from './components/create-lesson/create-lesson.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LogoutComponent } from './components/logout/logout.component';
import { EnrollCourseComponent } from './components/enroll-course/enroll-course.component';
import { ProfileComponent } from './components/profile/profile.component';
import { DiscussionBoardComponent } from './components/discussion-board/discussion-board.component';
import { TestComponent } from './components/test/test.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    LessonComponent,
    CourseComponent,
    NavbarComponent,
    CreateLessonComponent,
    LoginComponent,
    LogoutComponent,
    EnrollCourseComponent,
	ProfileComponent,
	DiscussionBoardComponent,
	TestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
