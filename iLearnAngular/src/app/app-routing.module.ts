import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateLessonComponent } from './components/create-lesson/create-lesson.component';
import { LoginComponent } from './components/login/login.component';
import { CourseComponent } from './components/course/course.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LogoutComponent } from './components/logout/logout.component';
import { ProfileComponent } from './components/profile/profile.component';

import { EnrollCourseComponent } from './components/enroll-course/enroll-course.component';

import { DiscussionBoardComponent } from './components/discussion-board/discussion-board.component';
import { TestComponent } from './components/test/test.component';


const routes: Routes = [
  {
    path: 'home',component: HomepageComponent
  },

  {
    path: "create-lesson",
    component: CreateLessonComponent
  },
  {
    path: '',
    component: LoginComponent

},
  {
    path: 'logout', component:LogoutComponent
  },
  {
    path: 'profile', component:ProfileComponent
  },
  {
    path: 'createCourse', component:TestComponent
  },
  {
    path: 'discussionBoard', component:DiscussionBoardComponent
  },
  {
    path:"course",
    component: CourseComponent

  },
  {
    path:"enroll-course",
    component: EnrollCourseComponent

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
