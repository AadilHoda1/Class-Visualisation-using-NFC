# Class-Visualisation-using-NFC
READ ME DOCUMENT
The app Classroom Visualisation without Facial Recognition
helps the teachers to track attention levels of the students and
view colour coded symbols for the same.

The app has three types of users:

Admin:
● Opens the app.
● Selects Admin option.
● Enters login credentials and signs in.
● Can add new admin, new student, new professor, and new classroom
info.

Student:
● Enters the classroom.
● Opens the app on his mobile device.
● Selects Student Option.
● Places device on NFC Tag.
● Attention level is re-generated every minute.

Professor:
● Enters the classroom.
● Opens the app on his mobile device.
● Selects Professor Option.
● Enters login credentials and signs in.
● Enters room number he wants to view.
● Classroom visualisation matrix is displayed.
● The matrix is refreshed every minute.
● Can touch on an occupied cell to view details of the student
sitting there.

Operating Environment:
The application will be designed for mobile devices running
Android OS.
● Android v5.0 or higher (API level 21 or higher)
● NFC enabled device
● All time access to internet
● Device must have a minimum of 100 MB storage space
for storing the application and also some minimal data
generated during operation.

System Interface Requirements:
This section contains all of the functional and quality
requirements of the system. It gives a detailed description of the
system and all its features.
User Interfaces:
● Login screen opens at launching the app.
● Logged in view shows the classroom as a matrix
● Each cell of the matrix is color coded, based on the
attention level of the student sitting in the seat
corresponding to the cell.
● On clicking on any cell in matrix, the corresponding
student’s info card pops up.

Hardware Interfaces:
● Android version 5.0 (Lollipop), i.e. API Level 21 or
Above.
● Wi-Fi 802.11ac or 802.11n (preferred) or mobile data
(3G or faster).
● NFC (Near Field Communication) enabled devices.

Software Interfaces:
The parent system also developed for Android Platform willbe directly interfaced with this product and thus won’t need
any external added APIs for communication.

Communication Interfaces:
There must be a local area connection / server to which all
the Android devices are connected while the lecture is being
delivered by the teacher. The state data will be transmitted
from the students’ device to the server and from the server
to the teacher’s Android device.

Non-functional Requirements:

Reliability and Performance Requirements:

● The performance of the product shall depend on the
hardware components of user’s devices.
● Screen size and resolution on the professor’s device
must be suitable to display the whole class.
● Students’ mobile devices must be NFC enabled.
● Both the students and the professors must be
connected to the internet for the application to work
Properly.

Usability Requirements:
● The app should not be closed i.e. the user can
switch to some other process in the smartphone
but the application should still be present in the
list of background processes.
● It must be ensured that the devices stay
connected to the internet during the whole
lecture.
Safety and Security Requirements:● The system will use a login system for authentication
and thus will be highly secure and will prevent any
type of unauthorized access to private content.
● NFC tags are used for student positioning and would
work properly unless tampered.

References:
● IEEE Std 830-1998 (Revision of IEEE Std 830-1993)
● IEEE Recommended Practice for Software Requirements
Specifications
● Android SDK : ​ https://developer.android.com
● Java : ​ https://docs.oracle.com/javase/7/docs/api
● NFC Documentation :
https://developer.android.com/guide/topics/connectivity/n
fc
