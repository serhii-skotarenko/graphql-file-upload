import {Component, OnInit} from '@angular/core';
import {User} from "./models/user";
import {Apollo, gql} from "apollo-angular";
import {DomSanitizer} from "@angular/platform-browser";

const GET_ALL_USERS = gql`
  query GetAllUsers {
    users {
      id
      name
      email
      avatar
    }
  }
`;

const CREATE_USER = gql`
  mutation CreateUser($user: CreateUserInput!, $avatar: Upload) {
    createUser(user: $user, avatar: $avatar) {
      id
      name
      email
    }
  }
`;

interface GetAllUsersResponse {
  users: [User]
}

interface CreateUserResponse {
  createUser: User
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  users: User[] = [];
  user: User = {
    id: '',
    name: '',
    email: '',
    avatar: ''
  };
  avatarFile: File = new File([], '');

  constructor(private apollo: Apollo,
              private sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
    this.fetchAllUsers();
  }

  createUser(): void {
    this.apollo.mutate<CreateUserResponse, any>({
      mutation: CREATE_USER,
      variables: {
        user: {
          name: this.user.name,
          email: this.user.email
        },
        avatar: this.avatarFile
      },
      context: {
        useMultipart: true
      }
    }).subscribe((response) => {
      if (response.data?.createUser) {
        this.fetchAllUsers();
      }
    });
  }

  onAvatarUpload(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      this.avatarFile = file;
    }
  }

  fetchAllUsers(): void {
    this.apollo.query<GetAllUsersResponse>({
      query: GET_ALL_USERS
    })
      .subscribe((response) => {
        this.users = response.data.users;
      });
  }

  public sanitizeUrl(url: string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

}
