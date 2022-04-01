import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {HttpClientModule} from "@angular/common/http";
import {APOLLO_OPTIONS, ApolloModule} from "apollo-angular";
import {HttpLink} from 'apollo-angular/http';
import {InMemoryCache} from '@apollo/client/core';
import {FormsModule} from "@angular/forms";
import {extractFiles} from "extract-files";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ApolloModule,
    FormsModule
  ],
  providers: [{
    provide: APOLLO_OPTIONS,
    useFactory: (httpLink: HttpLink) => {
      return {
        cache: new InMemoryCache({
          addTypename: false,
        }),
        defaultOptions: {
          query: {
            fetchPolicy: 'no-cache',
          },
          mutate: {
            fetchPolicy: 'no-cache',
          },
          watchQuery: {
            errorPolicy: 'all',
          },
        },
        link: httpLink.create({
          uri: 'http://localhost:8080/graphql',
          extractFiles
        })
      };
    },
    deps: [HttpLink]
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
