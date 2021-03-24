import { TestBed } from '@angular/core/testing';

import { EnrollCourseService } from './enroll-course.service';

describe('EnrollCourseService', () => {
  let service: EnrollCourseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnrollCourseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
